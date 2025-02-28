package io.tbbc.cf.rest;

import io.quarkus.logging.Log;
import io.tbbc.cf.common.BadArgumentException;
import io.tbbc.cf.common.InternalException;
import io.tbbc.cf.common.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

class ExceptionMappers {

    @ServerExceptionMapper
    public RestResponse<String> mapBadArgumentException(BadArgumentException exception) {
        Log.info(exception);
        return RestResponse.status(Response.Status.BAD_REQUEST, exception.getMessage());
    }

    @ServerExceptionMapper
    public RestResponse<String> mapNotFoundException(NotFoundException exception) {
        Log.info(exception);
        return RestResponse.status(Response.Status.NOT_FOUND, exception.getMessage());
    }

    @ServerExceptionMapper
    public RestResponse<String> mapInternalException(InternalException exception) {
        Log.error("Internal exception", exception);
        return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}