package com.imjcker.jrebel.JrebelUtil;

import org.apache.commons.lang3.StringUtils;

public class JrebelSign {
    private String signature;

    public void toLeaseCreateJson(String clientRandomness, final String guid, boolean offline, String validFrom, String validUntil) {
        String serverRandomness =  "H2ulzLlh7E0="; //服务端随机数,如果要自己生成，务必将其写到json的serverRandomness中
        String s2= "";
        if(offline){
            s2 = StringUtils.join((Object[]) new String[]{clientRandomness, serverRandomness, guid, String.valueOf(offline), validFrom, validUntil}, ';');
        }else{
            s2 = StringUtils.join((Object[]) new String[]{clientRandomness, serverRandomness, guid, String.valueOf(offline)}, ';');
        }
        System.out.println(s2);
        final byte[] a2 =LicenseServer2ToJRebelPrivateKey.a(s2.getBytes());
        this.signature = ByteUtil.a(a2);
    }

    public String getSignature() {
        return signature;
    }

}
