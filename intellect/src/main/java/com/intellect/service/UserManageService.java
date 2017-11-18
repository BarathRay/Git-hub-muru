package com.intellect.service;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import com.intellect.modal.ResponseWrapper;
import com.intellect.modal.User;

@Service
public class UserManageService  extends UserCoreService implements InitializingBean {


	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Autowired
	@Qualifier("userval2")
	private Validator validator2;


	@SuppressWarnings("unused")
	public ResponseWrapper createUser( User user, BindingResult bindingResult)
	{
		//Errors errorlist=(Errors) new ObjectError(); 
		try {
			if(bindingResult.hasErrors())
			{
				return	handleResponse(ResponseWrapper.INVALID_INPUT, "",bindingResult);			
			}

			String sel_sql="select id from USER where ISACTIVE=true and EMAIL='"+user.getEmail()+"'";

			String exist = null;
			try {
				exist = jdbcTemplate.queryForObject(sel_sql, String.class);
			} catch (EmptyResultDataAccessException e) {
				e.printStackTrace();
			}

			if(exist!=null)
			{
				return	handleResponse(ResponseWrapper.VALIDATION, "",new String[]{"User/email already exists"});
			}

			String ins_sql="INSERT INTO USER(FNAME, LNAME, EMAIL, PINCODE, BIRTHDATE, ISACTIVE )VALUES ( ?, ?, ?, ?, ?, ?)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(
					connection -> {
						PreparedStatement ps = connection.prepareStatement(ins_sql, new String[]{"id"});
						ps.setString(1, user.getfName());
						ps.setString(2, user.getlName());
						ps.setString(3, user.getEmail());
						ps.setDouble(4, user.getPinCode());
						ps.setDate(5,  user.getBirthDate());
						ps.setBoolean(6,  true);
						return ps;
					}, keyHolder);

			Collection<Object> map = keyHolder.getKeys().values();
			String userid=	map.toString();
			return	handleResponse(ResponseWrapper.SUCCESS, userid,(String[]) null);


		} catch (Exception e) {
			e.printStackTrace();
			return	handleResponse(ResponseWrapper.FAIL, "",(String[]) null);			
		}

	}



	public ResponseWrapper updateUser(User user, BindingResult bindingResult)
	{
		validator2.validate(user, bindingResult);
		try {
			if(bindingResult.hasErrors())
			{
				return	handleResponse(ResponseWrapper.INVALID_INPUT, "",bindingResult);			
			}

			String sel_sql="select id from USER where ISACTIVE=true and ID='"+user.getId()+"'";

			String exist = null;
			try {
				exist = jdbcTemplate.queryForObject(sel_sql, String.class);
			} catch (EmptyResultDataAccessException e) {
				e.printStackTrace();
			}

			if(exist==null)
			{
				return	handleResponse(ResponseWrapper.VALIDATION, "",new String[]{"User does not  exists.Enter valid id"});
			}

			String update_sql="update User set  PINCODE=?, BIRTHDATE=? where ID=? and  ISACTIVE=true ";

			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(update_sql, new String[]{"id"});
				ps.setDouble(1, user.getPinCode());
				ps.setDate(2, user.getBirthDate());
				ps.setString(3, user.getId());
				return ps;
			});

			return	handleResponse(ResponseWrapper.SUCCESS, user.getId(),(String[]) null);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return	handleResponse(ResponseWrapper.FAIL, "",(String[]) null);
		}


	}

	public ResponseWrapper updateUser(String userid)
	{

		try {
			String sel_sql="select id from USER where ISACTIVE=true and ID='"+userid+"'";

			String exist = null;
			try {
				exist = jdbcTemplate.queryForObject(sel_sql, String.class);
			} catch (EmptyResultDataAccessException e) {
				e.printStackTrace();
			}

			if(exist==null)
			{
				return	handleResponse(ResponseWrapper.VALIDATION, "",new String[]{"User does not  exists.Enter valid id"});
			}


			String update_sql="update User set ISACTIVE=false where ID=?  ";

			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(update_sql, new String[]{"id"});
				ps.setString(1, userid);

				return ps;
			});

			return	handleResponse(ResponseWrapper.SUCCESS, userid,(String[]) null);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return	handleResponse(ResponseWrapper.FAIL, "",(String[]) null);
		}


	}


	public List<User> collectionUsers()
	{
		return (List<User>) jdbcTemplate.query("select * from User where isActive=true", new BeanPropertyRowMapper(User.class));
	}



	@Override
	public void afterPropertiesSet() throws Exception {
		
		String sqlcreate="create sequence user_seq;"
				+ "CREATE TABLE IF NOT EXISTS PUBLIC.USER"
				+ "( ID    varchar(60)  default user_seq.nextval primary key"
				+ ", FNAME varchar(60), LNAME varchar(60), EMAIL varchar(60), PINCODE double, BIRTHDATE date, ISACTIVE boolean);";
		
		jdbcTemplate.update(sqlcreate);
		
	}


}
