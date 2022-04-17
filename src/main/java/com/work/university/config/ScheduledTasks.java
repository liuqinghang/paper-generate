package com.work.university.config;

import com.work.university.mapper.QuestionMapper;
import com.work.university.tools.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    QuestionMapper questionMapper;

    //每隔2s执行一次
    @Scheduled(fixedRate = 2000)
    public void reportCurrentTime() {
        Integer likes;
        try{
            //从redis中取出键为LIKE_KEY(Integer id)的值
//            Integer i = 1;
//            likes = (Integer)redisUtil.get("QV:"+i);
//            if(likes != null){
//                questionMapper.setLikesByPrimaryKey(likes,1);
//            }
        }catch (Exception e){
        }
    }

}
