using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Web.Script.Serialization;

namespace ConsoleApplication1
{
    class Program
    {
        public delegate void Del(string result, HttpStatusCode status);
        public enum HTTPMETHODS { GET, PUT, POST, DELETE };
        static void Main(string[] args)
        {
            new Program();
        }

        public Program()
        {
            Console.WriteLine("sadf");
            //als verweis hinzugefügt: System.Web.Extensions
            var thing = new Thing();
            var json = new JavaScriptSerializer().Serialize(thing);
            Console.WriteLine("#################JSON############");
            Console.WriteLine(json);
            Console.WriteLine("################/JSON############");

            string url = "https://query.yahooapis.com/v1/public/yql?q=SELECT%20*%20FROM%20data.html.cssselect%20WHERE%20url%3D'http%3A%2F%2Fwww.htl-villach.at%2Fnc%2Fschule%2Fkontakt%2Flehrer.html'%20AND%20css%3D'a%5Bhref*%3D%5C'lehrer%2Fid%5C'%5D'&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
            
            HttpGet(url, HTTPMETHODS.GET, null, new Del(useInformation));

            Console.WriteLine("#######################should be before callresult");
            Console.Read();
        }

        public void HttpGet(string url,HTTPMETHODS method, string content, Del del)
        {
            AsyncCall t = new AsyncCall(url, method ,content,del);
            Thread th = new Thread(new ThreadStart(t.runThread));

            th.Start();
        }

        public void useInformation(string result, HttpStatusCode status)
        {
            Console.WriteLine(result);
            Console.WriteLine(((int)status)+": "+status.ToString());
        }
    }
}
