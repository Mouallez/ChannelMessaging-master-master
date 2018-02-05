package william.servian.channelmessaging;

/**
 * Created by sacquind on 22/01/2018.
 */
public class Reponse {
    public class Response {
        private String response;
        private String code;
        private String accesstoken;

        public String getAccesstoken() {
            return accesstoken;
        }

        public void setAccesstoken(String accesstoken) {
            this.accesstoken = accesstoken;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getResponse() {

            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

        public Response(String response) {
            this.response = response;
        }
    }
}

