package com.example.michael.testapptwo;


import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.util.ArrayList;

/**
 * The app to be tested
 * @file MainActivity.java
 * @author Michael Jeffrey, Jason Huggins
 * @date 30/4/2017
 */

public class MainActivity extends AppCompatActivity {


    /**
     * The instance of the client to be used to communicate with the server
     */
    private WebSocketClient socket;
    /**
     * The instance of the URI to be used to initiate contact with the server
     */
    private URI uri;
    /**
     * The instance of the BufferedReader to be used to read incoming data from the server
     */
    private BufferedReader in;
    /**
     * The instance of PrintStream to be used to send data to the server
     */
    private PrintStream out;
    /**
     * Used to check that the entered IP Address is valid
     */
    private IPAddressValidator ipAddressValidator = new IPAddressValidator();
    /**
     * Key word to tell the robot to use the function 'move'
     */
    private static final String move = "move";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    /**
     * Converts a String array to a String
     * @param stringArray The String array to be converted
     * @return The converted String
     */
    public static String stringArraytoString(String[] stringArray) {
        String result ="";
        for(String x:stringArray) {
            result += x;
        }
        return result;
    }

    //TODO check if needed
//    private static byte[] stringToByte(String[] stringArray) {
//        ArrayList<Byte> tempData = new ArrayList<Byte>();
//        byte[] finishedData;
//        for (int i = 0; i < stringArray.length; i++) {
//            String string = stringArray[i];
//            finishedData = string.getBytes();
//            for(byte x:finishedData) {
//                tempData.add(x);
//            }
//        }
//        Byte[] dataByte = tempData.toArray(new Byte[tempData.size()]);
//        byte[] data = new byte[dataByte.length];
//        for(int i = 0; i < dataByte.length; i++) {
//            data[i] = dataByte[i];
//        }
//        return data;
//    }

//    private static String[] byteToString(byte[] byteArray) {
//        String[] data = new String[byteArray.length];
//        for (int i = 0; i < byteArray.length; i++) {
//            data[i] = new String(byteArray[i]);
//        }
//        return data;
//    }


    /**
     * Perform initialization of all fragments and loaders.
     * @param savedInstanceState Contains any previously initialised instances of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ArrayList<Button> buttons = new ArrayList<Button>();

        final String[] ipAddress = new String[1];

        final Button Tiny = (Button) findViewById(R.id.Tiny);
        final Button Small = (Button) findViewById(R.id.Small);
        final Button Medium = (Button) findViewById(R.id.Medium);
        final Button bt1 = (Button) findViewById(R.id.Bt1);
        final Button serverConnect = (Button) findViewById(R.id.ServerConnect);
        final Button serverDisconnect = (Button) findViewById(R.id.ServerDisconnect);

        buttons.add(Tiny);
        Tiny.setOnClickListener(new OnClickListener() {
            /**
             * Called when a view has been clicked
             * @param view The view that was clicked
             */
            @Override
            public void onClick(View view) {
                System.out.println("Tiny Clicked");
                String[] tiny = {move,"*","0","*","0","*","-140"};
                socket.send(stringArraytoString(tiny));
            }
        });

        buttons.add(Small);
        Small.setOnClickListener(new OnClickListener() {
            /**
             * Called when a view has been clicked
             * @param view The view that was clicked
             */
            @Override
            public void onClick(View view) {
                System.out.println("Small Clicked");
                String[] small = {move,"*","0","*","0","*","-140"};
                socket.send(stringArraytoString(small));
            }
        });

        buttons.add(Medium);
        Medium.setOnClickListener(new OnClickListener() {
            /**
             * Called when a view has been clicked
             * @param view The view that was clicked
             */
            @Override
            public void onClick(View view) {
                System.out.println("Medium Clicked");
                String[] medium = {move,"*","0","*","0","*","-140"};
                socket.send(stringArraytoString(medium));
            }
        });

        buttons.add(bt1);
        bt1.setOnClickListener(new OnClickListener() {
            /**
             * Called when a view has been clicked
             * @param view The view that was clicked
             */
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                System.out.println("Bt1 Clicked");
                socket.send("Example");
                //socket.send("STRING");
                //socket.send(buttons.toString());
                bt1.setVisibility(View.INVISIBLE);
                Tiny.setVisibility(View.VISIBLE);
                Small.setVisibility(View.VISIBLE);
                Medium.setVisibility(View.VISIBLE);

            }
        });
        buttons.add(serverDisconnect);
        serverDisconnect.setOnClickListener(new OnClickListener() {
            /**
             * Called when a view has been clicked
             * @param view The view that was clicked
             */
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                System.out.println("Server Disconnect Clicked");
                Toast.makeText(MainActivity.this, "Server Disconnect Clicked", Toast.LENGTH_SHORT).show();
                try{
                    socket.close();
                    bt1.setVisibility(View.INVISIBLE);
                    serverDisconnect.setVisibility(View.INVISIBLE);
                    serverConnect.setVisibility(View.VISIBLE);
                    Tiny.setVisibility(View.INVISIBLE);
                    Small.setVisibility(View.INVISIBLE);
                    Medium.setVisibility(View.INVISIBLE);
                } finally{}/* catch (InterruptedException e) {
                    System.out.println("MainActivity::serverDisconnect " + e);
                }*/
            }
        });

        buttons.add(serverConnect);
        serverConnect.setOnClickListener(new OnClickListener() {
            /**
             * Called when a view has been clicked
             * @param view The view that was clicked
             */
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                System.out.println("Server Connect Clicked");
                Toast.makeText(MainActivity.this, "Server Connect Clicked", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_ip_entry,null);
                final EditText mIPAddress = (EditText) mView.findViewById(R.id.etIPAddress);
                Button mConnect = (Button) mView.findViewById(R.id.btnConnect);
                final AlertDialog dialog;
                mBuilder.setView(mView);
                dialog = mBuilder.create();
                mConnect.setOnClickListener(new View.OnClickListener() {
                    /**
                     * Called when a view has been clicked. Reads in and validates the target IP
                     * Address
                     * @param view The view that was clicked
                     */
                    @Override
                    public void onClick(View view) {
                        //TODO Add check to ensure correct format for IP Address
                        String address = mIPAddress.getText().toString();
                        if(address.isEmpty()) {
                            Toast.makeText(MainActivity.this, R.string.no_ip_entered, Toast.LENGTH_SHORT).show();
                        } else if(!ipAddressValidator.validate(address)){
                            Toast.makeText(MainActivity.this, R.string.invalid_ip, Toast.LENGTH_SHORT).show();
                        }else{
                            //ipAddress[0] = mIPAddress.getText().toString();
                            Toast.makeText(MainActivity.this, R.string.successful_ip_entered, Toast.LENGTH_SHORT).show();
                            dialog.hide();
                            try {
                                //10.0.2.2
                                uri = new URI("ws://"+address+":8080/");
                            } catch (URISyntaxException e) {
                                System.out.println("MainActivity.java::serverConnect.setOnClickListener" + e);
                                Toast.makeText(MainActivity.this, R.string.failed_to_connect, Toast.LENGTH_LONG).show();
                            }
                            socket = new WebSocketClient(uri) {
                                /**
                                 * When a connection is made, print out "Connected"
                                 * @param handshakedata The data surrounding the handshake
                                 */
                                @Override
                                public void onOpen(ServerHandshake handshakedata) {
                                    //send("App connected");
                                    System.out.println("Connected");
                                }

                                /**
                                 * When a message is received, print it out
                                 * @param message The received message
                                 */
                                @Override
                                public void onMessage(String message) {
                                    //send(message);
                                    System.out.println("From server: " + message);
                                }

                                /**
                                 * When the connection is closed, print out the reason
                                 * @param code The exit code
                                 * @param reason The test representation of the code
                                 * @param remote Whether the connection was ended by the remote host
                                 */
                                @Override
                                public void onClose(int code, String reason, boolean remote) {
                                    System.out.println("Closed: " + code + " " + reason);
                                }

                                /**
                                 * When an error occurs, print out the error
                                 * @param ex The error
                                 */
                                @Override
                                public void onError(Exception ex) {
                                    System.out.println("Error: ");
                                    ex.printStackTrace();
                                }
                            };
                            socket.connect();
                            String[] setup = {move,"*","0","*","0","*","-140"};
                            int counter = 0;
                            while(!socket.isOpen()) {
                                counter++;
                            }
                            socket.send(stringArraytoString(setup));
                            bt1.setVisibility(View.VISIBLE);
                            serverDisconnect.setVisibility(View.VISIBLE);
                            serverConnect.setVisibility(View.INVISIBLE);
                        }
                    }

                });
                dialog.show();
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
