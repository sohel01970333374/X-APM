package github.tornaco.xposedmoduletest.x.bean;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by guohao4 on 2017/10/31.
 * Email: Tornaco@163.com
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlurSettings implements Parcelable {

    private boolean enabled;
    private int policy;
    private int radius;
    private float scale;

    protected BlurSettings(Parcel in) {
        enabled = in.readByte() != 0;
        policy = in.readInt();
        radius = in.readInt();
        scale = in.readFloat();
    }

    public static final Creator<BlurSettings> CREATOR = new Creator<BlurSettings>() {
        @Override
        public BlurSettings createFromParcel(Parcel in) {
            return new BlurSettings(in);
        }

        @Override
        public BlurSettings[] newArray(int size) {
            return new BlurSettings[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (enabled ? 1 : 0));
        dest.writeInt(policy);
        dest.writeInt(radius);
        dest.writeFloat(scale);
    }
}
