package palyshoping.example.com.shopingforplay.eventbus;

import com.squareup.otto.Bus;

/**
 * Created by Administrator on 2017/4/5/005.
 */

public class OttooBus extends Bus{

    private static final OttooBus BUS=new OttooBus();
    public static OttooBus getBus(){
        if (BUS==null){
            return new OttooBus();
        }
        return BUS;
    }
}
