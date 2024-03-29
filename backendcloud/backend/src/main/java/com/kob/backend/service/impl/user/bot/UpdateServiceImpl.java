package com.kob.backend.service.impl.user.bot;

import com.kob.backend.mapper.BotMapper;
import com.kob.backend.pojo.Bot;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.bot.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: chen
 * Time: 2024/1/30 18:52
 */
@Service
public class UpdateServiceImpl implements UpdateService {

    @Autowired
    private BotMapper botMapper;

    @Override
    public Map<String, String> update(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        Map<String, String> map = new HashMap<>();

        String str_bot_id = data.get("bot_id");
        if (str_bot_id == null || str_bot_id.length() == 0) {
            map.put("error_message", "Bot_id不能为空");
            return map;
        }

        int bot_id =Integer.parseInt(str_bot_id);
        String title = data.get("title");
        String description = data.get("description");
        String content = data.get("content");

        if (title == null || title.length() == 0) {
            map.put("error_message", "标题不能为空");
            return map;
        }
        if (title.length() > 100) {
            map.put("error_message", "标题长度不能大于100");
            return map;
        }
        if (description == null || description.length() == 0) {
            description = "这个用户很懒，什么也没留下~";
        }
        if (description.length() > 300) {
            map.put("error_message", "Bot描述长度不能大于300");
            return map;
        }
        if (content == null || content.length() == 0) {
            map.put("error_message", "代码不能为空");
            return map;
        }
        if (content.length() > 10000) {
            map.put("error_message", "代码长度不能大于10000");
            return map;
        }

        Bot bot = botMapper.selectById(bot_id);
        if (bot == null) {
            map.put("error_message", "Bot不存在或已被删除");
            return map;
        }

        if(!bot.getUserId().equals(user.getId())) {
            map.put("error_message", "没有权限修改");
            return map;
        }

        Bot new_bot = new Bot(
                bot.getId(),
                user.getId(),
                title,
                description,
                content,
                bot.getCreatetime(),
                new Date()
        );
        botMapper.updateById(new_bot);
        map.put("error_message", "success");

        return map;
    }
}
