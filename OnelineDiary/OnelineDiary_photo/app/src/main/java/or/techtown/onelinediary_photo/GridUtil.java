package or.techtown.onelinediary_photo;

import java.util.Map;

public class GridUtil {
    double gridX;
    double gridY;

    public GridUtil(double gridX, double gridY) {
        this.gridX = gridX;
        this.gridY = gridY;
    }

    public double getGridX() {
        return gridX;
    }

    public void setGridX(double gridX) {
        this.gridX = gridX;
    }

    public double getGridY() {
        return gridY;
    }

    public void setGridY(double gridY) {
        this.gridY = gridY;
    }
    
    // 경위도 좌표애서 격자 번호로 변환 과정 필요
    public static Map<String, Double> getGrid(double gridX, double gridY){
        Map<String, Double> gridMap = null;
        gridMap.put("x", gridX);
        gridMap.put("y", gridY);

        return gridMap;
    }
}
