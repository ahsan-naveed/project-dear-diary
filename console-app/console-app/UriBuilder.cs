using System;
using System.Threading;

namespace UriTests {

    public class TestUriProgram {
        public static Uri GetSimpleUri () {
            var builder = new UriBuilder {
                Scheme = "http",
                Host = "packt.com"
            };
            return builder.Uri;
        }

        public static void Main () {
            var simpleUri = GetSimpleUri();

            Console.WriteLine(simpleUri.ToString());
            Thread.Sleep(10000);
        }
    }

}