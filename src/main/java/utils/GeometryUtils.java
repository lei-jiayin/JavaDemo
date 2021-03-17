package utils;

import com.google.common.base.Joiner;
import lombok.Data;

import java.util.*;

/**
 * @author adv
 * @date 2021/1/29 14:49
 */
public class GeometryUtils {

    /**
     * 将一个经纬度数据转换成WKT能够解读的点的字符串
     *
     * @param warpLatitude
     * @return
     */
    private static String createPointString(Map<String, Object> warpLatitude) {
        return "POINT (" + warpLatitude + ")";
    }


    /**
     * 将多个经纬度数据转换成一个面
     *
     * @param warpLatitudes
     * @return
     */
    public static String createPolygonString(List<Map<String, Object>> warpLatitudes) {
        List<WarpLatitude> list = new ArrayList<>();
        for(Map<String, Object> m: warpLatitudes){
            WarpLatitude warpLatitude = new WarpLatitude(m.get("lng").toString() ,m.get("lat").toString());
            list.add(warpLatitude);
        }
        return "POLYGON((" + Joiner.on(",").join(list) +
                "))";
    }

}

@Data
class WarpLatitude {
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;

    public WarpLatitude(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return this.longitude + " " + this.latitude;
    }
}
