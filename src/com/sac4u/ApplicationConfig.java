package com.sac4u;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class ApplicationConfig extends Application {

    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<Class<?>>();

        // Add your resources.
        resources.add(UploadFileService.class);

        // Add additional features such as support for Multipart.
//        resources.add(MultiPartFeature.class);

        return resources;
    }
}
