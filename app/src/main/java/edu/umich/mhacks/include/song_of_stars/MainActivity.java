package edu.umich.mhacks.include.song_of_stars;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.Arm;
import com.thalmic.myo.DeviceListener;
import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.Quaternion;
import com.thalmic.myo.XDirection;
import com.thalmic.myo.scanner.ScanActivity;


public class MainActivity extends Activity {

    private SeekBar seekBar;
    private TextView connected;
    private TextView notFound;
    private NotePlayer notePlayer;
    private DeviceListener listener = new AbstractDeviceListener()
    {
        @Override
        public void onConnect(Myo myo, long timestamp)
        {
            // hide notFound, show connected
            notFound.setVisibility(View.INVISIBLE);
            connected.setVisibility(View.VISIBLE);
        }

        @Override
        public void onDisconnect(Myo myo, long timestamp)
        {
            // hide connected, show notFound
            notFound.setVisibility(View.VISIBLE);
            connected.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onArmSync(Myo myo, long timestamp, Arm arm, XDirection xDirection)
        {
            // show the seekbar, hide the others
            seekBar.setVisibility(View.VISIBLE);
            connected.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onArmUnsync(Myo myo, long timestamp)
        {
            // hide the seekbar, show connected
            seekBar.setVisibility(View.INVISIBLE);
            connected.setVisibility(View.VISIBLE);
        }

        @Override
        public void onOrientationData(Myo myo, long timestamp, Quaternion rotation)
        {
            float yaw = (float) Quaternion.yaw(rotation);

            // we care about yaw
            int val = (int) (50 - 50 * Math.sin(yaw));
            val = Math.min(100, val);
            val = Math.max(0, val);
            seekBar.setProgress(val);
        }

        @Override
        public void onPose(Myo myo, long timestamp, Pose pose)
        {
            switch (pose)
            {
                case DOUBLE_TAP:
                case FINGERS_SPREAD:
                case FIST:
                    int section = seekBar.getProgress()/15;
                    notePlayer.play(section);
                    break;
                case UNKNOWN:
                case REST:
                default:
                    // ignore
                    break;
            }
            myo.unlock(Myo.UnlockType.HOLD);
            myo.notifyUserAction();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notePlayer = new NotePlayer(this);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        connected = (TextView) findViewById(R.id.connected);
        notFound = (TextView) findViewById(R.id.notFound);

        Hub hub = Hub.getInstance();
        if (!hub.init(this, getPackageName()))
        {
            Toast.makeText(this, "Failed to initialize Myo", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // turn on bluetooth
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, RESULT_OK);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // do nothing
        }

        // attach to nearby myo
        hub.attachToAdjacentMyo();

        hub.addListener(listener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return false;
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
}
