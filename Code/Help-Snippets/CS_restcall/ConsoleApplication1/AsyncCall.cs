using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Web;

namespace ConsoleApplication1
{
    class AsyncCall
    {
        private String url;
        private ConsoleApplication1.Program.Del del;
        private string content;
        private Program.HTTPMETHODS method;

        public AsyncCall(String url, Program.HTTPMETHODS method, Program.Del del)
        {
            this.del = del;
            this.url = url;
            this.method = method;
        }

        public AsyncCall(String url, Program.HTTPMETHODS method,  String content, Program.Del del)
        {
            this.del = del;
            this.url = url;
            this.method = method;
            this.content = content;
        }

        public void runThread()
        {
            HttpWebRequest req = WebRequest.Create(new Uri(url)) as HttpWebRequest;
            req.Method = method.ToString();
            if (content != null)
            {
                req.ContentLength = this.content.Length;
                byte[] data = Encoding.ASCII.GetBytes(content);
                using (Stream stream = req.GetRequestStream())
                {
                    stream.Write(data, 0, data.Length);
                }
            }
            req.ContentType = "application/json";
            req.Accept = "application/json";
            using (HttpWebResponse resp = req.GetResponse() as HttpWebResponse)
            {
                StreamReader reader =  new StreamReader(resp.GetResponseStream());
                this.del( reader.ReadToEnd(),resp.StatusCode);
            }
        }

    }
}
