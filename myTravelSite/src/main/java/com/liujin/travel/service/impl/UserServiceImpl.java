package com.liujin.travel.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liujin.travel.dao.UserDao;
import com.liujin.travel.dao.impl.UserDaoImpl;
import com.liujin.travel.domain.ResultInfo;
import com.liujin.travel.domain.User;
import com.liujin.travel.service.UserService;
import com.liujin.travel.util.MailUtils;
import com.liujin.travel.util.UuidUtil;

/**
 * @program: myTravelSite
 * @description:
 * @author: liujin
 * @create: 2019-07-28 17:25
 **/
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public String register(User user) {
        ResultInfo info = new ResultInfo();
        User user1 = userDao.findByUsername(user.getUsername());

        if (user1 != null) {
            //用户存在，注册失败！
            info.setFlag(false);
            info.setErrorMsg("用户名已存在！");
        } else {
            //保存用户信息
            //设置激活码，唯一字符串
            user.setCode(UuidUtil.getUuid());
            //设置激活状态
            user.setStatus("N");
            int save = userDao.save(user);

            if (save > 0){
                String content = "<a href='http://localhost:80/travel/activeUserServlet?code="+user.getCode()+"'>点击激活【黑马旅游网】</a>";
                MailUtils.sendMail(user.getEmail(),content,"激活邮件");
                info.setFlag(true);
            }else {
                info.setFlag(false);
                info.setErrorMsg("注册失败");
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(info);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }
}
