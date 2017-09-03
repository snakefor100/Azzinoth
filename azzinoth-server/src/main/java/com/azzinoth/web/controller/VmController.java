package com.azzinoth.web.controller;

import com.azzinoth.server.VirtualMachineRegisterService;
import com.azzinoth.web.response.ResponseBase;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by niujunlong on 17/9/3.
 */
@RestController(value = "/virtualMachine")
public class VmController {
    @Resource
    private VirtualMachineRegisterService virtualMachineRegisterService;
    @GetMapping(value = "/register")
    public ResponseBase registerAgent(int javaPid){
        try {
            virtualMachineRegisterService.register("12",javaPid);
        }catch (Exception e){
           e.printStackTrace();
        }
        return new ResponseBase();
    }
}
