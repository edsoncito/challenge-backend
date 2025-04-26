package com.challenge.app.controllers;

import lombok.Generated;

public class ApiDataResponse<T> {

    private T data;

    private ApiDataResponse(T data) {
        this.data = data;
    }

    public static <T> ApiDataResponse<T> of(T data) {
        return new ApiDataResponse(data);
    }

    @Generated
    protected ApiDataResponse(final ApiDataResponseBuilder<T, ?, ?> b) {
        this.data = b.data;
    }

    @Generated
    public static <T> ApiDataResponseBuilder<T, ?, ?> builder() {
        return new ApiDataResponseBuilderImpl();
    }

    @Generated
    public T getData() {
        return this.data;
    }

    @Generated
    public ApiDataResponse() {
    }

    @Generated
    public abstract static class ApiDataResponseBuilder<T, C extends ApiDataResponse<T>, B extends ApiDataResponseBuilder<T, C, B>> {
        @Generated
        private T data;

        public ApiDataResponseBuilder() {
        }

        @Generated
        public B data(final T data) {
            this.data = data;
            return this.self();
        }

        @Generated
        protected abstract B self();

        @Generated
        public abstract C build();

        @Generated
        public String toString() {
            return "ApiDataResponse.ApiDataResponseBuilder(data=" + String.valueOf(this.data) + ")";
        }
    }

    @Generated
    private static final class ApiDataResponseBuilderImpl<T> extends ApiDataResponseBuilder<T, ApiDataResponse<T>, ApiDataResponseBuilderImpl<T>> {
        @Generated
        private ApiDataResponseBuilderImpl() {
        }

        @Generated
        protected ApiDataResponseBuilderImpl<T> self() {
            return this;
        }

        @Generated
        public ApiDataResponse<T> build() {
            return new ApiDataResponse(this);
        }
    }
}
