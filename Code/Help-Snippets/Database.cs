using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.Sql;
using System.Data.SqlClient;
using System.Data.OleDb;
using System.Data;
using System.Windows;

namespace Stadtplan
{
    class Database
    {
        private OleDbConnection Connection;


        public Database(string p)
        {
            Connection = new OleDbConnection(p);
            Connection.Open();
        }

        public IDataReader executeSelect(string query){

            IDbCommand selectCmd = new OleDbCommand(query, Connection);
            return selectCmd.ExecuteReader();
        }

        internal int executeNonSelect(string query)
        {
            IDbCommand selectCmd = new OleDbCommand(query, Connection);
            return selectCmd.ExecuteNonQuery();
        }


        /*SELECT c.name, t.X, t.Y, t.id
FROM stadtplan c,
TABLE(SDO_UTIL.GETVERTICES(c.shape)) t
where c.name = 'cola_b'
ORDER BY c.name,t.id;*/

        internal List<Point> getPoints(String p,string list)
        {
            List<Point> b = new List<Point>();
            String sql = "SELECT c.name, t.X, t.Y, t.id FROM " + list + " c, TABLE(SDO_UTIL.GETVERTICES(c.shape)) t where c.name =  '" + p + "' ORDER BY c.name,t.id";
            //Console.WriteLine(sql); 
            IDataReader reader = executeSelect(sql);
            while (reader.Read())
            {
                Point ptmp = new Point();
                ptmp.X = Convert.ToInt32(reader["X"]);
                ptmp.Y = Convert.ToInt32(reader["Y"]);
                b.Add(ptmp);
            }
            return b;
        }

        internal List<CityObject> getObjects(string list)
        {
            List<CityObject> b = new List<CityObject>();

            IDataReader reader = executeSelect("select name from " + list + "");
            
            while (reader.Read())
            {
                CityObject tmp = new CityObject(reader["name"].ToString());
                b.Add(tmp);
            }
            return b;
        }

        internal spatialType getType(String name, string list)
        {
            spatialType retValue = spatialType.POLYGON;
            IDataReader reader = executeSelect("SELECT e.column_value as x FROM "+list+" c, TABLE (c.shape.sdo_elem_info) e WHERE c.name = '"+name+"'");
            reader.Read();
            reader.Read();
            int etype = Convert.ToInt32(reader["x"]);
            reader.Read();
            int interpretation = Convert.ToInt32(reader["x"]);
            switch (etype)
            {
                case 2:
                    if (interpretation == 1)
                    {
                        retValue = spatialType.LINE;
                    }
                    break;

                case 2003:
                case 1003:
                    switch (interpretation)
                    {
                        case 1:
                            retValue = spatialType.POLYGON;
                            break;
                        case 3:
                            retValue = spatialType.RECTANGLE;
                            break;
                        case 4:
                            retValue = spatialType.CIRCLE;
                            break;
                    }
                    break;

                case 2001:
                    retValue = spatialType.POINT;
                    break;
            }
            return retValue;
        }


      /*  SELECT c.mkt_id, c.name
  FROM stadtplan c
  WHERE SDO_ANYINTERACT(c.shape,
            SDO_GEOMETRY(2003, NULL, NULL,
              SDO_ELEM_INFO_ARRAY(1,1003,3),
              SDO_ORDINATE_ARRAY(4,6, 8,8))
            ) = 'TRUE';
        */


        public void deletePeilung(int p)
        {
            string sql = "delete from peilung where peilung_id = " + p;
            this.executeNonSelect(sql);
        }

        internal List<CityObject> interSects(double x, double y,string type)
        {
            List<CityObject> b = new List<CityObject>();
            String sql = "SELECT c.id, c.name FROM "+ type +" c WHERE SDO_ANYINTERACT(c.shape, SDO_GEOMETRY(2001, NULL, sdo_point_type(" + x + "," + y + ",null), NULL,NULL)) = 'TRUE'";

            Console.WriteLine(sql);
            IDataReader reader = executeSelect(sql);

            while (reader.Read())
            {
                CityObject tmp = new CityObject(reader["name"].ToString());
                b.Add(tmp);
            }
            return b;
        }

        internal String getNearestHospital(double x, double y)
        {
            
            string sql = "select p.id, sdo_nn_distance(1), t.X, t.Y, p.name as name from punkt p, TABLE(SDO_UTIL.GETVERTICES(p.shape)) t where sdo_nn(p.shape,SDO_GEOMETRY(2001,NULL,SDO_POINT_TYPE(" + x + ", " + y + ", NULL),NULL,NULL),'sdo_num_res=1',1) = 'TRUE'";
            IDataReader reader = this.executeSelect(sql);
            while(reader.Read()){
                return reader["name"].ToString();
            }

            return "Keines";
        }

        internal void insertUnfall(double x, double y)
        {
            int maxid = -1;

            IDataReader reader = this.executeSelect("select NVL(MAX(peilung_id),-1) as max from peilung");
            reader.Read();
            maxid = Convert.ToInt32(reader["max"].ToString());
            maxid++;
            string sql = "insert into peilung values(" + maxid + ", sdo_geometry ( 2001, null, null, sdo_elem_info_array (1,1,1), sdo_ordinate_array (" + x + "," + y + ")) )";
            this.executeNonSelect(sql);
        }
    }
}
