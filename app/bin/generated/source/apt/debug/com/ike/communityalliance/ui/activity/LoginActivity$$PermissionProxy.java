// Generated code. Do not modify!
package com.ike.communityalliance.ui.activity;

import com.zhy.m.permission.*;

public class LoginActivity$$PermissionProxy implements PermissionProxy<LoginActivity> {
@Override
 public void grant(LoginActivity source , int requestCode) {
switch(requestCode) {case 1001:source.requestSdcardSuccess();break;}  }
@Override
 public void denied(LoginActivity source , int requestCode) {
switch(requestCode) {case 1001:source.requestSdcardFailed();break;}  }
@Override
 public void rationale(LoginActivity source , int requestCode) {
switch(requestCode) {}  }
@Override
 public boolean needShowRationale(int requestCode) {
switch(requestCode) {}
return false;  }

}
