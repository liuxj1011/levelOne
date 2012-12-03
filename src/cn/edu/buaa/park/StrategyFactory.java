package cn.edu.buaa.park;

/**
 * 策略工厂类
 * Created with IntelliJ IDEA.
 * User: 刘小军
 * Date: 12-12-3
 * Time: 下午5:21
 * To change this template use File | Settings | File Templates.
 */
public class StrategyFactory {
    public static Strategy getStrategy(String strategyName) {
        if("average".equals(strategyName)) {
            return new AverageStrategy();
        } else if("vacancyRate".equals(strategyName)) {
            return new VacancyRateStrategy();
        } else {
            return new DefaultStrategy();
        }
    }
}
