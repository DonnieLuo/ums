/**
 * Copyright (c) 2016-2016 All Rights Reserved.
 *//*

package com.controller;

import com.mobilityexchange.admin.web.mapper.ObjectMapper;
import com.mobilityexchange.admin.web.request.NameAuthRequest;
import com.mobilityexchange.admin.web.vo.UserVO;
import com.mobilityexchange.common.config.Constants;
import com.mobilityexchange.common.domain.enums.UserTypeEnum;
import com.mobilityexchange.common.response.Response;
import com.mobilityexchange.common.result.ResultEnum;
import com.mobilityexchange.common.security.api.TokenService;
import com.mobilityexchange.usersystem.api.domain.SysUser;
import com.mobilityexchange.usersystem.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

*/
/**
 * 鐧婚檰閴存潈鎺ュ彛
 *
 * @author ylin
 *//*

@Api(description = "鐧婚檰閴存潈鎺ュ彛")
@SuppressWarnings("unchecked")
@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation(value = "鍒涘缓鐢ㄦ埛")
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Response<SysUser>> insertUser(@Valid @RequestBody SysUser user) {
        if (StringUtils.isBlank(user.getPassword()) && StringUtils.isBlank(user.getUserName())) {
            return new Response(HttpStatus.BAD_REQUEST, "鐢ㄦ埛鍚嶅拰鐢佃瘽鍙风爜涓嶈兘鍚屾椂涓虹┖").build();
        }
        if (!StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        Response<SysUser> result = userService.insertUser(user);
        if (result.isSuccessWithData()) {
            result.getData().setPassword("");//鍒涘缓鍚庤缃瘑鐮佷负绌�
        }
        return result.build();
    }

    @ApiOperation(value = "鐢ㄦ埛鍚嶇櫥闄�", notes = "鏍规嵁鐢ㄦ埛鍚嶅拰瀵嗙爜杩涜鐢ㄦ埛鐧婚檰锛屼骇鐢焧oken")
    @RequestMapping(value = "/name", method = RequestMethod.POST)
    public ResponseEntity<Response<UserVO>> createAuthTokenByUsername(@Valid @RequestBody NameAuthRequest nameAuthRequest) throws AuthenticationException {
        String username = nameAuthRequest.getUsername();
        String password = nameAuthRequest.getPassword();
        try {
            Authentication authentication = tryToAuthenticateWithUsernameAndPassword(username, password);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            logger.info("鐢ㄦ埛鍚嶆垨瀵嗙爜閿欒:username={}", username);
            return new Response(ResultEnum.BAD_NAME_OR_PASSWORD).build();
        }

        final Response<SysUser> response = userService.getUserByUsername(nameAuthRequest.getUsername());
        return buildResponse(response).build();
    }

    @ApiOperation(value = "閫�鍑虹櫥褰�", notes = "")
    @ApiImplicitParam(name = Constants.TOKEN_HEADER, value = "鐢ㄦ埛token", paramType = "header", required = true, dataType = "String")
    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    public ResponseEntity<Response<Boolean>> logout(@RequestHeader(value = Constants.TOKEN_HEADER, defaultValue = "") String token) throws AuthenticationException {
        tokenService.deleteToken(token);
        SecurityContextHolder.clearContext();
        return new Response<>(Boolean.TRUE, ResultEnum.SUCCESS).build();
    }

    private Authentication tryToAuthenticateWithUsernameAndPassword(String username, String password) {
        UsernamePasswordAuthenticationToken requestAuthentication = new UsernamePasswordAuthenticationToken(username, password);
        return tryToAuthenticate(requestAuthentication);
    }

    private Response<UserVO> buildResponse(Response<SysUser> response) {
        if (response.isSuccessWithData()) {
            SysUser user = response.getData();
            final String token = tokenService.generateToken(user.getId(), UserTypeEnum.INTERNAL.getType(), user.getAuthorities());
            final UserVO userVO = ObjectMapper.INSTANCE.toUserVO(user, token);
            return new Response<>(userVO, ResultEnum.SUCCESS);
        }
        return new Response<>(response.getResult());
    }

    private Authentication tryToAuthenticate(Authentication requestAuthentication) {
        Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);
        if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
            throw new InternalAuthenticationServiceException("閴存潈澶辫触");
        }
        return responseAuthentication;
    }
}
*/
