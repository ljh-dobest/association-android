// Generated code. Do not modify!
package com.ike.communityalliance.ui.activity;

import com.zhy.m.permission.*;

public class AMAPLocationActivity$$PermissionProxy implements PermissionProxy<AMAPLocationActivity> {
@Override
 public void grant(AMAPLocationActivity source , int requestCode) {
switch(requestCode) {case 1005:source.requestSdcardSuccess();break;}  }
@Override
 public void denied(AMAPLocationActivity source , int requestCode) {
switch(requestCode) {case 1005:source.requestSdcardFailed();break;}  }
@Override
 public void rationale(AMAPLocationActivity source , int requestCode) {
switch(requestCode) {}  }
@Override
 public boolean needShowRationale(int requestCode) {
switch(requestCode) {}
return false;  }

}
