
package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.UserDeleteResponse;
import com.upgrad.quora.service.business.AdminService;
import com.upgrad.quora.service.entity.UserEntity;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AdminController {

    @Autowired
    private AdminService adminService;


    /***
     * Get the user details provided the userId.
     * @return
     * @throws AuthorizationFailedException - if the access token is invalid or already logged out or user is not an admin or user with enetered uuid does not exist
     * @throws UserNotFoundException - if the user with given id is not present in the records.
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/admin/user/{userId}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDeleteResponse> deleteUser(@PathVariable("userId") String userId,
                                                      @RequestHeader("authorization") final String accessToken) throws AuthorizationFailedException, UserNotFoundException {


        UserEntity userEntity = adminService.deleteUser( userId, accessToken);

        UserDeleteResponse userDeleteResponse = new UserDeleteResponse().id( userEntity.getUuid() )
                                                                   .status( "USER SUCCESSFULLY DELETED" );

        return new ResponseEntity<UserDeleteResponse>( userDeleteResponse,HttpStatus.OK );

    }
}
