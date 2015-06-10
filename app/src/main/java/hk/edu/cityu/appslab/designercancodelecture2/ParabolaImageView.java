package hk.edu.cityu.appslab.designercancodelecture2;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

public class ParabolaImageView extends ImageView {

    public static final String TAG = ParabolaImageView.class.getSimpleName();
    public static final double GRAVITY_CONSTANT = 9.80665;
    private int distanceX, distanceY;
    private double uV,uH;
    private float parabolaHeight;

    public ParabolaImageView(Context context) {
        super(context);
        init();
    }

    public ParabolaImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ParabolaImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ParabolaImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init(){
        uV = 0.5*GRAVITY_CONSTANT;
        final WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        distanceX = wm.getDefaultDisplay().getWidth()/2;
        distanceY = wm.getDefaultDisplay().getHeight()/2;
        uH = distanceX;
        Log.d(TAG, "init distanceX = " + distanceX + " uH = " + uH + " uV = " + uV);
        parabolaHeight = getResources().getDimension(R.dimen.parabola_height);
    }

    public int getDistanceX() {
        return distanceX;
    }

    public double getuV() {
        return uV;
    }

    public double getuH() {
        return uH;
    }

    public void setDistanceX(int distanceX) {
        this.distanceX = distanceX;

    }

    public void setXFraction(float xFraction){
        setTranslationX(xFraction* distanceX);
//        return (distanceX == 0) ? 0 : getY() / (float) distanceX;
    }

    public void setYFraction(float yFraction){
        setTranslationY(yFraction*distanceY);
    }

    public void setParabolaX(final float t) {
        //s=u*t+0.5*a*t*t
        if (distanceX >0) {
            if (t == 1) {
                setTranslationX(0);
            } else {
                double sH = uH * t;
                setTranslationX((float) -( distanceX - sH));
            }
        }
    }

    public void setParabolaY(final float t) {
        //s=u*t+0.5*a*t*t
        if (distanceX >0) {
            if (t == 1) {
                setTranslationY(0);
            } else {
                double sV = uV * t - 0.5 * GRAVITY_CONSTANT * t * t;
                setTranslationY((float) (-sV*parabolaHeight));
            }
        }
    }
}
