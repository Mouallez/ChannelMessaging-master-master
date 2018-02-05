package william.servian.channelmessaging;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by sacquind on 22/01/2018.
 */
public class ChannelActivity extends AppCompatActivity {
        private String channelID;
        private String nom;
        private String connectusers;

        public String getChannelID() {
            return channelID;
        }

        public void setChannelID(String channelID) {
            this.channelID = channelID;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String connectusers() {
            return connectusers;
        }

        public void connectusers(String connectusers) {
            this.connectusers = connectusers;
        }
}
