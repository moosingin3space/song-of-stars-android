package edu.umich.mhacks.include.song_of_stars;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.RemoteControlClient;
import android.provider.MediaStore;

/**
 * Created by nathan on 1/17/15.
 */
public class NotePlayer {
    private MediaPlayer f1;
    private MediaPlayer g1;
    private MediaPlayer a1;
    private MediaPlayer bb1;
    private MediaPlayer c1;
    private MediaPlayer d1;
    private MediaPlayer e1;
    private MediaPlayer f2;
    private MediaPlayer g2;
    private MediaPlayer a2;
    private MediaPlayer bb2;
    private MediaPlayer c2;
    private MediaPlayer d2;
    private MediaPlayer e2;
    private MediaPlayer f3;

    public NotePlayer(Context ctx) {
        f1 = MediaPlayer.create(ctx, R.raw.f1);
        g1 = MediaPlayer.create(ctx, R.raw.g1);
        a1 = MediaPlayer.create(ctx, R.raw.a1);
        bb1 = MediaPlayer.create(ctx, R.raw.bb1);
        c1 = MediaPlayer.create(ctx, R.raw.c1);
        d1 = MediaPlayer.create(ctx, R.raw.d1);
        e1 = MediaPlayer.create(ctx, R.raw.e1);
        f2 = MediaPlayer.create(ctx, R.raw.f2);
        g2 = MediaPlayer.create(ctx, R.raw.g2);
        a2 = MediaPlayer.create(ctx, R.raw.a2);
        bb2 = MediaPlayer.create(ctx, R.raw.bb2);
        c2 = MediaPlayer.create(ctx, R.raw.c2);
        d2 = MediaPlayer.create(ctx, R.raw.d2);
        e2 = MediaPlayer.create(ctx, R.raw.e2);
        f3 = MediaPlayer.create(ctx, R.raw.f3);
    }

    public void playF1()
    {
        f1.start();
    }

    public void playG1()
    {
        g1.start();
    }

    public void playA1()
    {
        a1.start();
    }

    public void playBb1()
    {
        bb1.start();
    }

    public void playC1()
    {
        c1.start();
    }

    public void playD1()
    {
        d1.start();
    }

    public void playE1()
    {
        e1.start();
    }

    public void playF2()
    {
        f2.start();
    }

    public void playG2()
    {
        g2.start();
    }

    public void playA2()
    {
        a2.start();
    }

    public void playBb2()
    {
        bb2.start();
    }

    public void playC2()
    {
        c2.start();
    }

    public void playD2()
    {
        d2.start();
    }

    public void playE2()
    {
        e2.start();
    }

    public void playF3()
    {
        f3.start();
    }

    public void play(int idx)
    {
        switch (idx)
        {
            case 0: playF1(); return;
            case 1: playG1(); return;
            case 2: playA1(); return;
            case 3: playBb1(); return;
            case 4: playC1(); return;
            case 5: playD1(); return;
            case 6: playE1(); return;
            case 7: playF2(); return;
            case 8: playG2(); return;
            case 9: playA2(); return;
            case 10: playBb2(); return;
            case 11: playC2(); return;
            case 12: playD2(); return;
            case 13: playE2(); return;
            case 14: playF3(); return;
        }
    }

    public void release()
    {
        f1.release();
        g1.release();
        a1.release();
        bb1.release();
        c1.release();
        d1.release();
        e1.release();
        f2.release();
        g2.release();
        a2.release();
        bb2.release();
        c2.release();
        d2.release();
        e2.release();
        f3.release();
    }
}
