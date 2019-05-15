package com.grom.server.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.grom.domain.dao.UserMapper;
import com.grom.domain.entity.User;
import com.grom.util.Query;
import com.grom.util.TableResultResponse;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SqlSession sqlSession;

    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public User getAll() {
        Long Longid = new Long(1);
        return userMapper.selectByPrimaryKey( Longid);
    }

    /**
     * 分页查询
     * @param params
     * @return
     */
    public TableResultResponse<User> list(Map<String, Object> params) {
        Query query = new Query(params);
        query.setPage(1);
        query.setLimit(10);
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<User> users = sqlSession.selectList("sql.user.selectByQueryMap", query);
        return new TableResultResponse<User>(result.getTotal(), users);
    }
}