import com.liujin.dao.CityDao;
import com.liujin.dao.impl.CityDaoImpl;
import com.liujin.domain.Province;
import org.junit.Test;

import java.util.List;

/**
 * @program: myProvinceAndCity
 * @description:
 * @author: liujin
 * @create: 2019-08-01 15:48
 **/
public class CityTest {
    @Test
    public void test01(){
        CityDao cityDao = new CityDaoImpl();
        List<Province> province = cityDao.findProvince();

        System.out.println(province);
    }
}
