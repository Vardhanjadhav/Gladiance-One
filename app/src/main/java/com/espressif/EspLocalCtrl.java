package com.espressif;

import espressif.Constants;

public final class EspLocalCtrl {
    private EspLocalCtrl() {}
    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistryLite registry) {
    }
    /**
     * Protobuf enum {@code rm_local_ctrl.LocalCtrlMsgType}
     */
    public enum LocalCtrlMsgType
            implements com.google.protobuf.Internal.EnumLite {
        /**
         * <code>TypeCmdGetPropertyCount = 0;</code>
         */
        TypeCmdGetPropertyCount(0),
        /**
         * <code>TypeRespGetPropertyCount = 1;</code>
         */
        TypeRespGetPropertyCount(1),
        /**
         * <code>TypeCmdGetPropertyValues = 4;</code>
         */
        TypeCmdGetPropertyValues(4),
        /**
         * <code>TypeRespGetPropertyValues = 5;</code>
         */
        TypeRespGetPropertyValues(5),
        /**
         * <code>TypeCmdSetPropertyValues = 6;</code>
         */
        TypeCmdSetPropertyValues(6),
        /**
         * <code>TypeRespSetPropertyValues = 7;</code>
         */
        TypeRespSetPropertyValues(7),
        UNRECOGNIZED(-1),
        ;

        /**
         * <code>TypeCmdGetPropertyCount = 0;</code>
         */
        public static final int TypeCmdGetPropertyCount_VALUE = 0;
        /**
         * <code>TypeRespGetPropertyCount = 1;</code>
         */
        public static final int TypeRespGetPropertyCount_VALUE = 1;
        /**
         * <code>TypeCmdGetPropertyValues = 4;</code>
         */
        public static final int TypeCmdGetPropertyValues_VALUE = 4;
        /**
         * <code>TypeRespGetPropertyValues = 5;</code>
         */
        public static final int TypeRespGetPropertyValues_VALUE = 5;
        /**
         * <code>TypeCmdSetPropertyValues = 6;</code>
         */
        public static final int TypeCmdSetPropertyValues_VALUE = 6;
        /**
         * <code>TypeRespSetPropertyValues = 7;</code>
         */
        public static final int TypeRespSetPropertyValues_VALUE = 7;


        @java.lang.Override
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new java.lang.IllegalArgumentException(
                        "Can't get the number of an unknown enum value.");
            }
            return value;
        }

        /**
         * @param value The number of the enum to look for.
         * @return The enum associated with the given number.
         * @deprecated Use {@link #forNumber(int)} instead.
         */
        @java.lang.Deprecated
        public static LocalCtrlMsgType valueOf(int value) {
            return forNumber(value);
        }

        public static LocalCtrlMsgType forNumber(int value) {
            switch (value) {
                case 0: return TypeCmdGetPropertyCount;
                case 1: return TypeRespGetPropertyCount;
                case 4: return TypeCmdGetPropertyValues;
                case 5: return TypeRespGetPropertyValues;
                case 6: return TypeCmdSetPropertyValues;
                case 7: return TypeRespSetPropertyValues;
                default: return null;
            }
        }

        public static com.google.protobuf.Internal.EnumLiteMap<LocalCtrlMsgType>
        internalGetValueMap() {
            return internalValueMap;
        }
        private static final com.google.protobuf.Internal.EnumLiteMap<
                LocalCtrlMsgType> internalValueMap =
                new com.google.protobuf.Internal.EnumLiteMap<LocalCtrlMsgType>() {
                    @java.lang.Override
                    public LocalCtrlMsgType findValueByNumber(int number) {
                        return LocalCtrlMsgType.forNumber(number);
                    }
                };

        public static com.google.protobuf.Internal.EnumVerifier
        internalGetVerifier() {
            return LocalCtrlMsgTypeVerifier.INSTANCE;
        }

        private static final class LocalCtrlMsgTypeVerifier implements
                com.google.protobuf.Internal.EnumVerifier {
            static final com.google.protobuf.Internal.EnumVerifier           INSTANCE = new LocalCtrlMsgTypeVerifier();
            @java.lang.Override
            public boolean isInRange(int number) {
                return LocalCtrlMsgType.forNumber(number) != null;
            }
        };

        private final int value;

        private LocalCtrlMsgType(int value) {
            this.value = value;
        }

        // @@protoc_insertion_point(enum_scope:rm_local_ctrl.LocalCtrlMsgType)
    }

    public interface CmdGetPropertyCountOrBuilder extends
            // @@protoc_insertion_point(interface_extends:rm_local_ctrl.CmdGetPropertyCount)
            com.google.protobuf.MessageLiteOrBuilder {
    }
    /**
     * Protobuf type {@code rm_local_ctrl.CmdGetPropertyCount}
     */
    public  static final class CmdGetPropertyCount extends
            com.google.protobuf.GeneratedMessageLite<
                    CmdGetPropertyCount, CmdGetPropertyCount.Builder> implements
            // @@protoc_insertion_point(message_implements:rm_local_ctrl.CmdGetPropertyCount)
            CmdGetPropertyCountOrBuilder {
        private CmdGetPropertyCount() {
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyCount parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyCount parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyCount parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyCount parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyCount parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyCount parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyCount parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyCount parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyCount parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyCount parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyCount parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyCount parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }
        public static Builder newBuilder(com.espressif.EspLocalCtrl.CmdGetPropertyCount prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /**
         * Protobuf type {@code rm_local_ctrl.CmdGetPropertyCount}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageLite.Builder<
                        com.espressif.EspLocalCtrl.CmdGetPropertyCount, Builder> implements
                // @@protoc_insertion_point(builder_implements:rm_local_ctrl.CmdGetPropertyCount)
                com.espressif.EspLocalCtrl.CmdGetPropertyCountOrBuilder {
            // Construct using rm_local_ctrl.EspLocalCtrl.CmdGetPropertyCount.newBuilder()
            private Builder() {
                super(DEFAULT_INSTANCE);
            }


            // @@protoc_insertion_point(builder_scope:rm_local_ctrl.CmdGetPropertyCount)
        }
        @java.lang.Override
        @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
        protected final java.lang.Object dynamicMethod(
                com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
                java.lang.Object arg0, java.lang.Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE: {
                    return new com.espressif.EspLocalCtrl.CmdGetPropertyCount();
                }
                case NEW_BUILDER: {
                    return new Builder();
                }
                case BUILD_MESSAGE_INFO: {
                    java.lang.Object[] objects = null;java.lang.String info =
                            "\u0000\u0000";
                    return newMessageInfo(DEFAULT_INSTANCE, info, objects);
                }
                // fall through
                case GET_DEFAULT_INSTANCE: {
                    return DEFAULT_INSTANCE;
                }
                case GET_PARSER: {
                    com.google.protobuf.Parser<com.espressif.EspLocalCtrl.CmdGetPropertyCount> parser = PARSER;
                    if (parser == null) {
                        synchronized (com.espressif.EspLocalCtrl.CmdGetPropertyCount.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser =
                                        new DefaultInstanceBasedParser<com.espressif.EspLocalCtrl.CmdGetPropertyCount>(
                                                DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                }
                case GET_MEMOIZED_IS_INITIALIZED: {
                    return (byte) 1;
                }
                case SET_MEMOIZED_IS_INITIALIZED: {
                    return null;
                }
            }
            throw new UnsupportedOperationException();
        }


        // @@protoc_insertion_point(class_scope:rm_local_ctrl.CmdGetPropertyCount)
        private static final com.espressif.EspLocalCtrl.CmdGetPropertyCount DEFAULT_INSTANCE;
        static {
            CmdGetPropertyCount defaultInstance = new CmdGetPropertyCount();
            // New instances are implicitly immutable so no need to make
            // immutable.
            DEFAULT_INSTANCE = defaultInstance;
            com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
                    CmdGetPropertyCount.class, defaultInstance);
        }

        public static com.espressif.EspLocalCtrl.CmdGetPropertyCount getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static volatile com.google.protobuf.Parser<CmdGetPropertyCount> PARSER;

        public static com.google.protobuf.Parser<CmdGetPropertyCount> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface RespGetPropertyCountOrBuilder extends
            // @@protoc_insertion_point(interface_extends:rm_local_ctrl.RespGetPropertyCount)
            com.google.protobuf.MessageLiteOrBuilder {

        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @return The enum numeric value on the wire for status.
         */
        int getStatusValue();
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @return The status.
         */
        Constants.Status getStatus();

        /**
         * <code>uint32 count = 2;</code>
         * @return The count.
         */
        int getCount();
    }
    /**
     * Protobuf type {@code rm_local_ctrl.RespGetPropertyCount}
     */
    public  static final class RespGetPropertyCount extends
            com.google.protobuf.GeneratedMessageLite<
                    RespGetPropertyCount, RespGetPropertyCount.Builder> implements
            // @@protoc_insertion_point(message_implements:rm_local_ctrl.RespGetPropertyCount)
            RespGetPropertyCountOrBuilder {
        private RespGetPropertyCount() {
        }
        public static final int STATUS_FIELD_NUMBER = 1;
        private int status_;
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @return The enum numeric value on the wire for status.
         */
        @java.lang.Override
        public int getStatusValue() {
            return status_;
        }
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @return The status.
         */
        @java.lang.Override
        public Constants.Status getStatus() {
            Constants.Status result = Constants.Status.forNumber(status_);
            return result == null ? Constants.Status.UNRECOGNIZED : result;
        }
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @param value The enum numeric value on the wire for status to set.
         */
        private void setStatusValue(int value) {
            status_ = value;
        }
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @param value The status to set.
         */
        private void setStatus(Constants.Status value) {
            status_ = value.getNumber();

        }
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         */
        private void clearStatus() {

            status_ = 0;
        }

        public static final int COUNT_FIELD_NUMBER = 2;
        private int count_;
        /**
         * <code>uint32 count = 2;</code>
         * @return The count.
         */
        @java.lang.Override
        public int getCount() {
            return count_;
        }
        /**
         * <code>uint32 count = 2;</code>
         * @param value The count to set.
         */
        private void setCount(int value) {

            count_ = value;
        }
        /**
         * <code>uint32 count = 2;</code>
         */
        private void clearCount() {

            count_ = 0;
        }

        public static com.espressif.EspLocalCtrl.RespGetPropertyCount parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.RespGetPropertyCount parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.RespGetPropertyCount parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.RespGetPropertyCount parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.RespGetPropertyCount parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.RespGetPropertyCount parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.RespGetPropertyCount parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.RespGetPropertyCount parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.RespGetPropertyCount parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.RespGetPropertyCount parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.RespGetPropertyCount parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.RespGetPropertyCount parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }
        public static Builder newBuilder(com.espressif.EspLocalCtrl.RespGetPropertyCount prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /**
         * Protobuf type {@code rm_local_ctrl.RespGetPropertyCount}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageLite.Builder<
                        com.espressif.EspLocalCtrl.RespGetPropertyCount, Builder> implements
                // @@protoc_insertion_point(builder_implements:rm_local_ctrl.RespGetPropertyCount)
                com.espressif.EspLocalCtrl.RespGetPropertyCountOrBuilder {
            // Construct using rm_local_ctrl.EspLocalCtrl.RespGetPropertyCount.newBuilder()
            private Builder() {
                super(DEFAULT_INSTANCE);
            }


            /**
             * <code>.rm_local_ctrl.Status status = 1;</code>
             * @return The enum numeric value on the wire for status.
             */
            @java.lang.Override
            public int getStatusValue() {
                return instance.getStatusValue();
            }
            /**
             * <code>.rm_local_ctrl.Status status = 1;</code>
             * @param value The status to set.
             * @return This builder for chaining.
             */
            public Builder setStatusValue(int value) {
                copyOnWrite();
                instance.setStatusValue(value);
                return this;
            }
            /**
             * <code>.rm_local_ctrl.Status status = 1;</code>
             * @return The status.
             */
            @java.lang.Override
            public Constants.Status getStatus() {
                return instance.getStatus();
            }
            /**
             * <code>.rm_local_ctrl.Status status = 1;</code>
             * @param value The enum numeric value on the wire for status to set.
             * @return This builder for chaining.
             */
            public Builder setStatus(Constants.Status value) {
                copyOnWrite();
                instance.setStatus(value);
                return this;
            }
            /**
             * <code>.rm_local_ctrl.Status status = 1;</code>
             * @return This builder for chaining.
             */
            public Builder clearStatus() {
                copyOnWrite();
                instance.clearStatus();
                return this;
            }

            /**
             * <code>uint32 count = 2;</code>
             * @return The count.
             */
            @java.lang.Override
            public int getCount() {
                return instance.getCount();
            }
            /**
             * <code>uint32 count = 2;</code>
             * @param value The count to set.
             * @return This builder for chaining.
             */
            public Builder setCount(int value) {
                copyOnWrite();
                instance.setCount(value);
                return this;
            }
            /**
             * <code>uint32 count = 2;</code>
             * @return This builder for chaining.
             */
            public Builder clearCount() {
                copyOnWrite();
                instance.clearCount();
                return this;
            }

            // @@protoc_insertion_point(builder_scope:rm_local_ctrl.RespGetPropertyCount)
        }
        @java.lang.Override
        @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
        protected final java.lang.Object dynamicMethod(
                com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
                java.lang.Object arg0, java.lang.Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE: {
                    return new com.espressif.EspLocalCtrl.RespGetPropertyCount();
                }
                case NEW_BUILDER: {
                    return new Builder();
                }
                case BUILD_MESSAGE_INFO: {
                    java.lang.Object[] objects = new java.lang.Object[] {
                            "status_",
                            "count_",
                    };
                    java.lang.String info =
                            "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002\u000b" +
                                    "";
                    return newMessageInfo(DEFAULT_INSTANCE, info, objects);
                }
                // fall through
                case GET_DEFAULT_INSTANCE: {
                    return DEFAULT_INSTANCE;
                }
                case GET_PARSER: {
                    com.google.protobuf.Parser<com.espressif.EspLocalCtrl.RespGetPropertyCount> parser = PARSER;
                    if (parser == null) {
                        synchronized (com.espressif.EspLocalCtrl.RespGetPropertyCount.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser =
                                        new DefaultInstanceBasedParser<com.espressif.EspLocalCtrl.RespGetPropertyCount>(
                                                DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                }
                case GET_MEMOIZED_IS_INITIALIZED: {
                    return (byte) 1;
                }
                case SET_MEMOIZED_IS_INITIALIZED: {
                    return null;
                }
            }
            throw new UnsupportedOperationException();
        }


        // @@protoc_insertion_point(class_scope:rm_local_ctrl.RespGetPropertyCount)
        private static final com.espressif.EspLocalCtrl.RespGetPropertyCount DEFAULT_INSTANCE;
        static {
            RespGetPropertyCount defaultInstance = new RespGetPropertyCount();
            // New instances are implicitly immutable so no need to make
            // immutable.
            DEFAULT_INSTANCE = defaultInstance;
            com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
                    RespGetPropertyCount.class, defaultInstance);
        }

        public static com.espressif.EspLocalCtrl.RespGetPropertyCount getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static volatile com.google.protobuf.Parser<RespGetPropertyCount> PARSER;

        public static com.google.protobuf.Parser<RespGetPropertyCount> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface PropertyInfoOrBuilder extends
            // @@protoc_insertion_point(interface_extends:rm_local_ctrl.PropertyInfo)
            com.google.protobuf.MessageLiteOrBuilder {

        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @return The enum numeric value on the wire for status.
         */
        int getStatusValue();
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @return The status.
         */
        Constants.Status getStatus();

        /**
         * <code>string name = 2;</code>
         * @return The name.
         */
        java.lang.String getName();
        /**
         * <code>string name = 2;</code>
         * @return The bytes for name.
         */
        com.google.protobuf.ByteString
        getNameBytes();

        /**
         * <code>uint32 type = 3;</code>
         * @return The type.
         */
        int getType();

        /**
         * <code>uint32 flags = 4;</code>
         * @return The flags.
         */
        int getFlags();

        /**
         * <code>bytes value = 5;</code>
         * @return The value.
         */
        com.google.protobuf.ByteString getValue();
    }
    /**
     * Protobuf type {@code rm_local_ctrl.PropertyInfo}
     */
    public  static final class PropertyInfo extends
            com.google.protobuf.GeneratedMessageLite<
                    PropertyInfo, PropertyInfo.Builder> implements
            // @@protoc_insertion_point(message_implements:rm_local_ctrl.PropertyInfo)
            PropertyInfoOrBuilder {
        private PropertyInfo() {
            name_ = "";
            value_ = com.google.protobuf.ByteString.EMPTY;
        }
        public static final int STATUS_FIELD_NUMBER = 1;
        private int status_;
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @return The enum numeric value on the wire for status.
         */
        @java.lang.Override
        public int getStatusValue() {
            return status_;
        }
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @return The status.
         */
        @java.lang.Override
        public Constants.Status getStatus() {
            Constants.Status result = Constants.Status.forNumber(status_);
            return result == null ? Constants.Status.UNRECOGNIZED : result;
        }
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @param value The enum numeric value on the wire for status to set.
         */
        private void setStatusValue(int value) {
            status_ = value;
        }
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @param value The status to set.
         */
        private void setStatus(Constants.Status value) {
            status_ = value.getNumber();

        }
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         */
        private void clearStatus() {

            status_ = 0;
        }

        public static final int NAME_FIELD_NUMBER = 2;
        private java.lang.String name_;
        /**
         * <code>string name = 2;</code>
         * @return The name.
         */
        @java.lang.Override
        public java.lang.String getName() {
            return name_;
        }
        /**
         * <code>string name = 2;</code>
         * @return The bytes for name.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString
        getNameBytes() {
            return com.google.protobuf.ByteString.copyFromUtf8(name_);
        }
        /**
         * <code>string name = 2;</code>
         * @param value The name to set.
         */
        private void setName(
                java.lang.String value) {
            java.lang.Class<?> valueClass = value.getClass();

            name_ = value;
        }
        /**
         * <code>string name = 2;</code>
         */
        private void clearName() {

            name_ = getDefaultInstance().getName();
        }
        /**
         * <code>string name = 2;</code>
         * @param value The bytes for name to set.
         */
        private void setNameBytes(
                com.google.protobuf.ByteString value) {
            checkByteStringIsUtf8(value);
            name_ = value.toStringUtf8();

        }

        public static final int TYPE_FIELD_NUMBER = 3;
        private int type_;
        /**
         * <code>uint32 type = 3;</code>
         * @return The type.
         */
        @java.lang.Override
        public int getType() {
            return type_;
        }
        /**
         * <code>uint32 type = 3;</code>
         * @param value The type to set.
         */
        private void setType(int value) {

            type_ = value;
        }
        /**
         * <code>uint32 type = 3;</code>
         */
        private void clearType() {

            type_ = 0;
        }

        public static final int FLAGS_FIELD_NUMBER = 4;
        private int flags_;
        /**
         * <code>uint32 flags = 4;</code>
         * @return The flags.
         */
        @java.lang.Override
        public int getFlags() {
            return flags_;
        }
        /**
         * <code>uint32 flags = 4;</code>
         * @param value The flags to set.
         */
        private void setFlags(int value) {

            flags_ = value;
        }
        /**
         * <code>uint32 flags = 4;</code>
         */
        private void clearFlags() {

            flags_ = 0;
        }

        public static final int VALUE_FIELD_NUMBER = 5;
        private com.google.protobuf.ByteString value_;
        /**
         * <code>bytes value = 5;</code>
         * @return The value.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getValue() {
            return value_;
        }
        /**
         * <code>bytes value = 5;</code>
         * @param value The value to set.
         */
        private void setValue(com.google.protobuf.ByteString value) {
            java.lang.Class<?> valueClass = value.getClass();

            value_ = value;
        }
        /**
         * <code>bytes value = 5;</code>
         */
        private void clearValue() {

            value_ = getDefaultInstance().getValue();
        }

        public static com.espressif.EspLocalCtrl.PropertyInfo parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.PropertyInfo parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.PropertyInfo parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.PropertyInfo parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.PropertyInfo parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.PropertyInfo parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.PropertyInfo parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.PropertyInfo parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.PropertyInfo parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.PropertyInfo parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.PropertyInfo parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.PropertyInfo parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }
        public static Builder newBuilder(com.espressif.EspLocalCtrl.PropertyInfo prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /**
         * Protobuf type {@code rm_local_ctrl.PropertyInfo}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageLite.Builder<
                        com.espressif.EspLocalCtrl.PropertyInfo, Builder> implements
                // @@protoc_insertion_point(builder_implements:rm_local_ctrl.PropertyInfo)
                com.espressif.EspLocalCtrl.PropertyInfoOrBuilder {
            // Construct using rm_local_ctrl.EspLocalCtrl.PropertyInfo.newBuilder()
            private Builder() {
                super(DEFAULT_INSTANCE);
            }


            /**
             * <code>.rm_local_ctrl.Status status = 1;</code>
             * @return The enum numeric value on the wire for status.
             */
            @java.lang.Override
            public int getStatusValue() {
                return instance.getStatusValue();
            }
            /**
             * <code>.rm_local_ctrl.Status status = 1;</code>
             * @param value The status to set.
             * @return This builder for chaining.
             */
            public Builder setStatusValue(int value) {
                copyOnWrite();
                instance.setStatusValue(value);
                return this;
            }
            /**
             * <code>.rm_local_ctrl.Status status = 1;</code>
             * @return The status.
             */
            @java.lang.Override
            public Constants.Status getStatus() {
                return instance.getStatus();
            }
            /**
             * <code>.rm_local_ctrl.Status status = 1;</code>
             * @param value The enum numeric value on the wire for status to set.
             * @return This builder for chaining.
             */
            public Builder setStatus(Constants.Status value) {
                copyOnWrite();
                instance.setStatus(value);
                return this;
            }
            /**
             * <code>.rm_local_ctrl.Status status = 1;</code>
             * @return This builder for chaining.
             */
            public Builder clearStatus() {
                copyOnWrite();
                instance.clearStatus();
                return this;
            }

            /**
             * <code>string name = 2;</code>
             * @return The name.
             */
            @java.lang.Override
            public java.lang.String getName() {
                return instance.getName();
            }
            /**
             * <code>string name = 2;</code>
             * @return The bytes for name.
             */
            @java.lang.Override
            public com.google.protobuf.ByteString
            getNameBytes() {
                return instance.getNameBytes();
            }
            /**
             * <code>string name = 2;</code>
             * @param value The name to set.
             * @return This builder for chaining.
             */
            public Builder setName(
                    java.lang.String value) {
                copyOnWrite();
                instance.setName(value);
                return this;
            }
            /**
             * <code>string name = 2;</code>
             * @return This builder for chaining.
             */
            public Builder clearName() {
                copyOnWrite();
                instance.clearName();
                return this;
            }
            /**
             * <code>string name = 2;</code>
             * @param value The bytes for name to set.
             * @return This builder for chaining.
             */
            public Builder setNameBytes(
                    com.google.protobuf.ByteString value) {
                copyOnWrite();
                instance.setNameBytes(value);
                return this;
            }

            /**
             * <code>uint32 type = 3;</code>
             * @return The type.
             */
            @java.lang.Override
            public int getType() {
                return instance.getType();
            }
            /**
             * <code>uint32 type = 3;</code>
             * @param value The type to set.
             * @return This builder for chaining.
             */
            public Builder setType(int value) {
                copyOnWrite();
                instance.setType(value);
                return this;
            }
            /**
             * <code>uint32 type = 3;</code>
             * @return This builder for chaining.
             */
            public Builder clearType() {
                copyOnWrite();
                instance.clearType();
                return this;
            }

            /**
             * <code>uint32 flags = 4;</code>
             * @return The flags.
             */
            @java.lang.Override
            public int getFlags() {
                return instance.getFlags();
            }
            /**
             * <code>uint32 flags = 4;</code>
             * @param value The flags to set.
             * @return This builder for chaining.
             */
            public Builder setFlags(int value) {
                copyOnWrite();
                instance.setFlags(value);
                return this;
            }
            /**
             * <code>uint32 flags = 4;</code>
             * @return This builder for chaining.
             */
            public Builder clearFlags() {
                copyOnWrite();
                instance.clearFlags();
                return this;
            }

            /**
             * <code>bytes value = 5;</code>
             * @return The value.
             */
            @java.lang.Override
            public com.google.protobuf.ByteString getValue() {
                return instance.getValue();
            }
            /**
             * <code>bytes value = 5;</code>
             * @param value The value to set.
             * @return This builder for chaining.
             */
            public Builder setValue(com.google.protobuf.ByteString value) {
                copyOnWrite();
                instance.setValue(value);
                return this;
            }
            /**
             * <code>bytes value = 5;</code>
             * @return This builder for chaining.
             */
            public Builder clearValue() {
                copyOnWrite();
                instance.clearValue();
                return this;
            }

            // @@protoc_insertion_point(builder_scope:rm_local_ctrl.PropertyInfo)
        }
        @java.lang.Override
        @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
        protected final java.lang.Object dynamicMethod(
                com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
                java.lang.Object arg0, java.lang.Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE: {
                    return new com.espressif.EspLocalCtrl.PropertyInfo();
                }
                case NEW_BUILDER: {
                    return new Builder();
                }
                case BUILD_MESSAGE_INFO: {
                    java.lang.Object[] objects = new java.lang.Object[] {
                            "status_",
                            "name_",
                            "type_",
                            "flags_",
                            "value_",
                    };
                    java.lang.String info =
                            "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001\f\u0002\u0208" +
                                    "\u0003\u000b\u0004\u000b\u0005\n";
                    return newMessageInfo(DEFAULT_INSTANCE, info, objects);
                }
                // fall through
                case GET_DEFAULT_INSTANCE: {
                    return DEFAULT_INSTANCE;
                }
                case GET_PARSER: {
                    com.google.protobuf.Parser<com.espressif.EspLocalCtrl.PropertyInfo> parser = PARSER;
                    if (parser == null) {
                        synchronized (com.espressif.EspLocalCtrl.PropertyInfo.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser =
                                        new DefaultInstanceBasedParser<com.espressif.EspLocalCtrl.PropertyInfo>(
                                                DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                }
                case GET_MEMOIZED_IS_INITIALIZED: {
                    return (byte) 1;
                }
                case SET_MEMOIZED_IS_INITIALIZED: {
                    return null;
                }
            }
            throw new UnsupportedOperationException();
        }


        // @@protoc_insertion_point(class_scope:rm_local_ctrl.PropertyInfo)
        private static final com.espressif.EspLocalCtrl.PropertyInfo DEFAULT_INSTANCE;
        static {
            PropertyInfo defaultInstance = new PropertyInfo();
            // New instances are implicitly immutable so no need to make
            // immutable.
            DEFAULT_INSTANCE = defaultInstance;
            com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
                    PropertyInfo.class, defaultInstance);
        }

        public static com.espressif.EspLocalCtrl.PropertyInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static volatile com.google.protobuf.Parser<PropertyInfo> PARSER;

        public static com.google.protobuf.Parser<PropertyInfo> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface CmdGetPropertyValuesOrBuilder extends
            // @@protoc_insertion_point(interface_extends:rm_local_ctrl.CmdGetPropertyValues)
            com.google.protobuf.MessageLiteOrBuilder {

        /**
         * <code>repeated uint32 indices = 1;</code>
         * @return A list containing the indices.
         */
        java.util.List<java.lang.Integer> getIndicesList();
        /**
         * <code>repeated uint32 indices = 1;</code>
         * @return The count of indices.
         */
        int getIndicesCount();
        /**
         * <code>repeated uint32 indices = 1;</code>
         * @param index The index of the element to return.
         * @return The indices at the given index.
         */
        int getIndices(int index);
    }
    /**
     * Protobuf type {@code rm_local_ctrl.CmdGetPropertyValues}
     */
    public  static final class CmdGetPropertyValues extends
            com.google.protobuf.GeneratedMessageLite<
                    CmdGetPropertyValues, CmdGetPropertyValues.Builder> implements
            // @@protoc_insertion_point(message_implements:rm_local_ctrl.CmdGetPropertyValues)
            CmdGetPropertyValuesOrBuilder {
        private CmdGetPropertyValues() {
            indices_ = emptyIntList();
        }
        public static final int INDICES_FIELD_NUMBER = 1;
        private com.google.protobuf.Internal.IntList indices_;
        /**
         * <code>repeated uint32 indices = 1;</code>
         * @return A list containing the indices.
         */
        @java.lang.Override
        public java.util.List<java.lang.Integer>
        getIndicesList() {
            return indices_;
        }
        /**
         * <code>repeated uint32 indices = 1;</code>
         * @return The count of indices.
         */
        @java.lang.Override
        public int getIndicesCount() {
            return indices_.size();
        }
        /**
         * <code>repeated uint32 indices = 1;</code>
         * @param index The index of the element to return.
         * @return The indices at the given index.
         */
        @java.lang.Override
        public int getIndices(int index) {
            return indices_.getInt(index);
        }
        private int indicesMemoizedSerializedSize = -1;
        private void ensureIndicesIsMutable() {
            com.google.protobuf.Internal.IntList tmp = indices_;
            if (!tmp.isModifiable()) {
                indices_ =
                        com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
            }
        }
        /**
         * <code>repeated uint32 indices = 1;</code>
         * @param index The index to set the value at.
         * @param value The indices to set.
         */
        private void setIndices(
                int index, int value) {
            ensureIndicesIsMutable();
            indices_.setInt(index, value);
        }
        /**
         * <code>repeated uint32 indices = 1;</code>
         * @param value The indices to add.
         */
        private void addIndices(int value) {
            ensureIndicesIsMutable();
            indices_.addInt(value);
        }
        /**
         * <code>repeated uint32 indices = 1;</code>
         * @param values The indices to add.
         */
        private void addAllIndices(
                java.lang.Iterable<? extends java.lang.Integer> values) {
            ensureIndicesIsMutable();
            com.google.protobuf.AbstractMessageLite.addAll(
                    values, indices_);
        }
        /**
         * <code>repeated uint32 indices = 1;</code>
         */
        private void clearIndices() {
            indices_ = emptyIntList();
        }

        public static com.espressif.EspLocalCtrl.CmdGetPropertyValues parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyValues parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyValues parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyValues parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyValues parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyValues parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyValues parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyValues parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyValues parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyValues parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyValues parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.CmdGetPropertyValues parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }
        public static Builder newBuilder(com.espressif.EspLocalCtrl.CmdGetPropertyValues prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /**
         * Protobuf type {@code rm_local_ctrl.CmdGetPropertyValues}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageLite.Builder<
                        com.espressif.EspLocalCtrl.CmdGetPropertyValues, Builder> implements
                // @@protoc_insertion_point(builder_implements:rm_local_ctrl.CmdGetPropertyValues)
                com.espressif.EspLocalCtrl.CmdGetPropertyValuesOrBuilder {
            // Construct using rm_local_ctrl.EspLocalCtrl.CmdGetPropertyValues.newBuilder()
            private Builder() {
                super(DEFAULT_INSTANCE);
            }


            /**
             * <code>repeated uint32 indices = 1;</code>
             * @return A list containing the indices.
             */
            @java.lang.Override
            public java.util.List<java.lang.Integer>
            getIndicesList() {
                return java.util.Collections.unmodifiableList(
                        instance.getIndicesList());
            }
            /**
             * <code>repeated uint32 indices = 1;</code>
             * @return The count of indices.
             */
            @java.lang.Override
            public int getIndicesCount() {
                return instance.getIndicesCount();
            }
            /**
             * <code>repeated uint32 indices = 1;</code>
             * @param index The index of the element to return.
             * @return The indices at the given index.
             */
            @java.lang.Override
            public int getIndices(int index) {
                return instance.getIndices(index);
            }
            /**
             * <code>repeated uint32 indices = 1;</code>
             * @param value The indices to set.
             * @return This builder for chaining.
             */
            public Builder setIndices(
                    int index, int value) {
                copyOnWrite();
                instance.setIndices(index, value);
                return this;
            }
            /**
             * <code>repeated uint32 indices = 1;</code>
             * @param value The indices to add.
             * @return This builder for chaining.
             */
            public Builder addIndices(int value) {
                copyOnWrite();
                instance.addIndices(value);
                return this;
            }
            /**
             * <code>repeated uint32 indices = 1;</code>
             * @param values The indices to add.
             * @return This builder for chaining.
             */
            public Builder addAllIndices(
                    java.lang.Iterable<? extends java.lang.Integer> values) {
                copyOnWrite();
                instance.addAllIndices(values);
                return this;
            }
            /**
             * <code>repeated uint32 indices = 1;</code>
             * @return This builder for chaining.
             */
            public Builder clearIndices() {
                copyOnWrite();
                instance.clearIndices();
                return this;
            }

            // @@protoc_insertion_point(builder_scope:rm_local_ctrl.CmdGetPropertyValues)
        }
        @java.lang.Override
        @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
        protected final java.lang.Object dynamicMethod(
                com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
                java.lang.Object arg0, java.lang.Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE: {
                    return new com.espressif.EspLocalCtrl.CmdGetPropertyValues();
                }
                case NEW_BUILDER: {
                    return new Builder();
                }
                case BUILD_MESSAGE_INFO: {
                    java.lang.Object[] objects = new java.lang.Object[] {
                            "indices_",
                    };
                    java.lang.String info =
                            "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001+";
                    return newMessageInfo(DEFAULT_INSTANCE, info, objects);
                }
                // fall through
                case GET_DEFAULT_INSTANCE: {
                    return DEFAULT_INSTANCE;
                }
                case GET_PARSER: {
                    com.google.protobuf.Parser<com.espressif.EspLocalCtrl.CmdGetPropertyValues> parser = PARSER;
                    if (parser == null) {
                        synchronized (com.espressif.EspLocalCtrl.CmdGetPropertyValues.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser =
                                        new DefaultInstanceBasedParser<com.espressif.EspLocalCtrl.CmdGetPropertyValues>(
                                                DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                }
                case GET_MEMOIZED_IS_INITIALIZED: {
                    return (byte) 1;
                }
                case SET_MEMOIZED_IS_INITIALIZED: {
                    return null;
                }
            }
            throw new UnsupportedOperationException();
        }


        // @@protoc_insertion_point(class_scope:rm_local_ctrl.CmdGetPropertyValues)
        private static final com.espressif.EspLocalCtrl.CmdGetPropertyValues DEFAULT_INSTANCE;
        static {
            CmdGetPropertyValues defaultInstance = new CmdGetPropertyValues();
            // New instances are implicitly immutable so no need to make
            // immutable.
            DEFAULT_INSTANCE = defaultInstance;
            com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
                    CmdGetPropertyValues.class, defaultInstance);
        }

        public static com.espressif.EspLocalCtrl.CmdGetPropertyValues getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static volatile com.google.protobuf.Parser<CmdGetPropertyValues> PARSER;

        public static com.google.protobuf.Parser<CmdGetPropertyValues> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface RespGetPropertyValuesOrBuilder extends
            // @@protoc_insertion_point(interface_extends:rm_local_ctrl.RespGetPropertyValues)
            com.google.protobuf.MessageLiteOrBuilder {

        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @return The enum numeric value on the wire for status.
         */
        int getStatusValue();
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @return The status.
         */
        Constants.Status getStatus();

        /**
         * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
         */
        java.util.List<com.espressif.EspLocalCtrl.PropertyInfo>
        getPropsList();
        /**
         * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
         */
        com.espressif.EspLocalCtrl.PropertyInfo getProps(int index);
        /**
         * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
         */
        int getPropsCount();
    }
    /**
     * Protobuf type {@code rm_local_ctrl.RespGetPropertyValues}
     */
    public  static final class RespGetPropertyValues extends
            com.google.protobuf.GeneratedMessageLite<
                    RespGetPropertyValues, RespGetPropertyValues.Builder> implements
            // @@protoc_insertion_point(message_implements:rm_local_ctrl.RespGetPropertyValues)
            RespGetPropertyValuesOrBuilder {
        private RespGetPropertyValues() {
            props_ = emptyProtobufList();
        }
        public static final int STATUS_FIELD_NUMBER = 1;
        private int status_;
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @return The enum numeric value on the wire for status.
         */
        @java.lang.Override
        public int getStatusValue() {
            return status_;
        }
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @return The status.
         */
        @java.lang.Override
        public Constants.Status getStatus() {
            Constants.Status result = Constants.Status.forNumber(status_);
            return result == null ? Constants.Status.UNRECOGNIZED : result;
        }
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @param value The enum numeric value on the wire for status to set.
         */
        private void setStatusValue(int value) {
            status_ = value;
        }
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @param value The status to set.
         */
        private void setStatus(Constants.Status value) {
            status_ = value.getNumber();

        }
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         */
        private void clearStatus() {

            status_ = 0;
        }

        public static final int PROPS_FIELD_NUMBER = 2;
        private com.google.protobuf.Internal.ProtobufList<com.espressif.EspLocalCtrl.PropertyInfo> props_;
        /**
         * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
         */
        @java.lang.Override
        public java.util.List<com.espressif.EspLocalCtrl.PropertyInfo> getPropsList() {
            return props_;
        }
        /**
         * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
         */
        public java.util.List<? extends com.espressif.EspLocalCtrl.PropertyInfoOrBuilder>
        getPropsOrBuilderList() {
            return props_;
        }
        /**
         * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
         */
        @java.lang.Override
        public int getPropsCount() {
            return props_.size();
        }
        /**
         * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
         */
        @java.lang.Override
        public com.espressif.EspLocalCtrl.PropertyInfo getProps(int index) {
            return props_.get(index);
        }
        /**
         * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
         */
        public com.espressif.EspLocalCtrl.PropertyInfoOrBuilder getPropsOrBuilder(
                int index) {
            return props_.get(index);
        }
        private void ensurePropsIsMutable() {
            com.google.protobuf.Internal.ProtobufList<com.espressif.EspLocalCtrl.PropertyInfo> tmp = props_;
            if (!tmp.isModifiable()) {
                props_ =
                        com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
            }
        }

        /**
         * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
         */
        private void setProps(
                int index, com.espressif.EspLocalCtrl.PropertyInfo value) {
            value.getClass();
            ensurePropsIsMutable();
            props_.set(index, value);
        }
        /**
         * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
         */
        private void addProps(com.espressif.EspLocalCtrl.PropertyInfo value) {
            value.getClass();
            ensurePropsIsMutable();
            props_.add(value);
        }
        /**
         * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
         */
        private void addProps(
                int index, com.espressif.EspLocalCtrl.PropertyInfo value) {
            value.getClass();
            ensurePropsIsMutable();
            props_.add(index, value);
        }
        /**
         * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
         */
        private void addAllProps(
                java.lang.Iterable<? extends com.espressif.EspLocalCtrl.PropertyInfo> values) {
            ensurePropsIsMutable();
            com.google.protobuf.AbstractMessageLite.addAll(
                    values, props_);
        }
        /**
         * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
         */
        private void clearProps() {
            props_ = emptyProtobufList();
        }
        /**
         * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
         */
        private void removeProps(int index) {
            ensurePropsIsMutable();
            props_.remove(index);
        }

        public static com.espressif.EspLocalCtrl.RespGetPropertyValues parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.RespGetPropertyValues parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.RespGetPropertyValues parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.RespGetPropertyValues parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.RespGetPropertyValues parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.RespGetPropertyValues parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.RespGetPropertyValues parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.RespGetPropertyValues parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.RespGetPropertyValues parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.RespGetPropertyValues parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.RespGetPropertyValues parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.RespGetPropertyValues parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }
        public static Builder newBuilder(com.espressif.EspLocalCtrl.RespGetPropertyValues prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /**
         * Protobuf type {@code rm_local_ctrl.RespGetPropertyValues}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageLite.Builder<
                        com.espressif.EspLocalCtrl.RespGetPropertyValues, Builder> implements
                // @@protoc_insertion_point(builder_implements:rm_local_ctrl.RespGetPropertyValues)
                com.espressif.EspLocalCtrl.RespGetPropertyValuesOrBuilder {
            // Construct using rm_local_ctrl.EspLocalCtrl.RespGetPropertyValues.newBuilder()
            private Builder() {
                super(DEFAULT_INSTANCE);
            }


            /**
             * <code>.rm_local_ctrl.Status status = 1;</code>
             * @return The enum numeric value on the wire for status.
             */
            @java.lang.Override
            public int getStatusValue() {
                return instance.getStatusValue();
            }
            /**
             * <code>.rm_local_ctrl.Status status = 1;</code>
             * @param value The status to set.
             * @return This builder for chaining.
             */
            public Builder setStatusValue(int value) {
                copyOnWrite();
                instance.setStatusValue(value);
                return this;
            }
            /**
             * <code>.rm_local_ctrl.Status status = 1;</code>
             * @return The status.
             */
            @java.lang.Override
            public Constants.Status getStatus() {
                return instance.getStatus();
            }
            /**
             * <code>.rm_local_ctrl.Status status = 1;</code>
             * @param value The enum numeric value on the wire for status to set.
             * @return This builder for chaining.
             */
            public Builder setStatus(Constants.Status value) {
                copyOnWrite();
                instance.setStatus(value);
                return this;
            }
            /**
             * <code>.rm_local_ctrl.Status status = 1;</code>
             * @return This builder for chaining.
             */
            public Builder clearStatus() {
                copyOnWrite();
                instance.clearStatus();
                return this;
            }

            /**
             * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
             */
            @java.lang.Override
            public java.util.List<com.espressif.EspLocalCtrl.PropertyInfo> getPropsList() {
                return java.util.Collections.unmodifiableList(
                        instance.getPropsList());
            }
            /**
             * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
             */
            @java.lang.Override
            public int getPropsCount() {
                return instance.getPropsCount();
            }/**
             * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
             */
            @java.lang.Override
            public com.espressif.EspLocalCtrl.PropertyInfo getProps(int index) {
                return instance.getProps(index);
            }
            /**
             * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
             */
            public Builder setProps(
                    int index, com.espressif.EspLocalCtrl.PropertyInfo value) {
                copyOnWrite();
                instance.setProps(index, value);
                return this;
            }
            /**
             * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
             */
            public Builder setProps(
                    int index, com.espressif.EspLocalCtrl.PropertyInfo.Builder builderForValue) {
                copyOnWrite();
                instance.setProps(index,
                        builderForValue.build());
                return this;
            }
            /**
             * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
             */
            public Builder addProps(com.espressif.EspLocalCtrl.PropertyInfo value) {
                copyOnWrite();
                instance.addProps(value);
                return this;
            }
            /**
             * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
             */
            public Builder addProps(
                    int index, com.espressif.EspLocalCtrl.PropertyInfo value) {
                copyOnWrite();
                instance.addProps(index, value);
                return this;
            }
            /**
             * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
             */
            public Builder addProps(
                    com.espressif.EspLocalCtrl.PropertyInfo.Builder builderForValue) {
                copyOnWrite();
                instance.addProps(builderForValue.build());
                return this;
            }
            /**
             * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
             */
            public Builder addProps(
                    int index, com.espressif.EspLocalCtrl.PropertyInfo.Builder builderForValue) {
                copyOnWrite();
                instance.addProps(index,
                        builderForValue.build());
                return this;
            }
            /**
             * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
             */
            public Builder addAllProps(
                    java.lang.Iterable<? extends com.espressif.EspLocalCtrl.PropertyInfo> values) {
                copyOnWrite();
                instance.addAllProps(values);
                return this;
            }
            /**
             * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
             */
            public Builder clearProps() {
                copyOnWrite();
                instance.clearProps();
                return this;
            }
            /**
             * <code>repeated .rm_local_ctrl.PropertyInfo props = 2;</code>
             */
            public Builder removeProps(int index) {
                copyOnWrite();
                instance.removeProps(index);
                return this;
            }

            // @@protoc_insertion_point(builder_scope:rm_local_ctrl.RespGetPropertyValues)
        }
        @java.lang.Override
        @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
        protected final java.lang.Object dynamicMethod(
                com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
                java.lang.Object arg0, java.lang.Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE: {
                    return new com.espressif.EspLocalCtrl.RespGetPropertyValues();
                }
                case NEW_BUILDER: {
                    return new Builder();
                }
                case BUILD_MESSAGE_INFO: {
                    java.lang.Object[] objects = new java.lang.Object[] {
                            "status_",
                            "props_",
                            com.espressif.EspLocalCtrl.PropertyInfo.class,
                    };
                    java.lang.String info =
                            "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\f\u0002\u001b" +
                                    "";
                    return newMessageInfo(DEFAULT_INSTANCE, info, objects);
                }
                // fall through
                case GET_DEFAULT_INSTANCE: {
                    return DEFAULT_INSTANCE;
                }
                case GET_PARSER: {
                    com.google.protobuf.Parser<com.espressif.EspLocalCtrl.RespGetPropertyValues> parser = PARSER;
                    if (parser == null) {
                        synchronized (com.espressif.EspLocalCtrl.RespGetPropertyValues.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser =
                                        new DefaultInstanceBasedParser<com.espressif.EspLocalCtrl.RespGetPropertyValues>(
                                                DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                }
                case GET_MEMOIZED_IS_INITIALIZED: {
                    return (byte) 1;
                }
                case SET_MEMOIZED_IS_INITIALIZED: {
                    return null;
                }
            }
            throw new UnsupportedOperationException();
        }


        // @@protoc_insertion_point(class_scope:rm_local_ctrl.RespGetPropertyValues)
        private static final com.espressif.EspLocalCtrl.RespGetPropertyValues DEFAULT_INSTANCE;
        static {
            RespGetPropertyValues defaultInstance = new RespGetPropertyValues();
            // New instances are implicitly immutable so no need to make
            // immutable.
            DEFAULT_INSTANCE = defaultInstance;
            com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
                    RespGetPropertyValues.class, defaultInstance);
        }

        public static com.espressif.EspLocalCtrl.RespGetPropertyValues getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static volatile com.google.protobuf.Parser<RespGetPropertyValues> PARSER;

        public static com.google.protobuf.Parser<RespGetPropertyValues> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface PropertyValueOrBuilder extends
            // @@protoc_insertion_point(interface_extends:rm_local_ctrl.PropertyValue)
            com.google.protobuf.MessageLiteOrBuilder {

        /**
         * <code>uint32 index = 1;</code>
         * @return The index.
         */
        int getIndex();

        /**
         * <code>bytes value = 2;</code>
         * @return The value.
         */
        com.google.protobuf.ByteString getValue();
    }
    /**
     * Protobuf type {@code rm_local_ctrl.PropertyValue}
     */
    public  static final class PropertyValue extends
            com.google.protobuf.GeneratedMessageLite<
                    PropertyValue, PropertyValue.Builder> implements
            // @@protoc_insertion_point(message_implements:rm_local_ctrl.PropertyValue)
            PropertyValueOrBuilder {
        private PropertyValue() {
            value_ = com.google.protobuf.ByteString.EMPTY;
        }
        public static final int INDEX_FIELD_NUMBER = 1;
        private int index_;
        /**
         * <code>uint32 index = 1;</code>
         * @return The index.
         */
        @java.lang.Override
        public int getIndex() {
            return index_;
        }
        /**
         * <code>uint32 index = 1;</code>
         * @param value The index to set.
         */
        private void setIndex(int value) {

            index_ = value;
        }
        /**
         * <code>uint32 index = 1;</code>
         */
        private void clearIndex() {

            index_ = 0;
        }

        public static final int VALUE_FIELD_NUMBER = 2;
        private com.google.protobuf.ByteString value_;
        /**
         * <code>bytes value = 2;</code>
         * @return The value.
         */
        @java.lang.Override
        public com.google.protobuf.ByteString getValue() {
            return value_;
        }
        /**
         * <code>bytes value = 2;</code>
         * @param value The value to set.
         */
        private void setValue(com.google.protobuf.ByteString value) {
            java.lang.Class<?> valueClass = value.getClass();

            value_ = value;
        }
        /**
         * <code>bytes value = 2;</code>
         */
        private void clearValue() {

            value_ = getDefaultInstance().getValue();
        }

        public static com.espressif.EspLocalCtrl.PropertyValue parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.PropertyValue parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.PropertyValue parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.PropertyValue parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.PropertyValue parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.PropertyValue parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.PropertyValue parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.PropertyValue parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.PropertyValue parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.PropertyValue parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.PropertyValue parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.PropertyValue parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }
        public static Builder newBuilder(com.espressif.EspLocalCtrl.PropertyValue prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /**
         * Protobuf type {@code rm_local_ctrl.PropertyValue}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageLite.Builder<
                        com.espressif.EspLocalCtrl.PropertyValue, Builder> implements
                // @@protoc_insertion_point(builder_implements:rm_local_ctrl.PropertyValue)
                com.espressif.EspLocalCtrl.PropertyValueOrBuilder {
            // Construct using rm_local_ctrl.EspLocalCtrl.PropertyValue.newBuilder()
            private Builder() {
                super(DEFAULT_INSTANCE);
            }


            /**
             * <code>uint32 index = 1;</code>
             * @return The index.
             */
            @java.lang.Override
            public int getIndex() {
                return instance.getIndex();
            }
            /**
             * <code>uint32 index = 1;</code>
             * @param value The index to set.
             * @return This builder for chaining.
             */
            public Builder setIndex(int value) {
                copyOnWrite();
                instance.setIndex(value);
                return this;
            }
            /**
             * <code>uint32 index = 1;</code>
             * @return This builder for chaining.
             */
            public Builder clearIndex() {
                copyOnWrite();
                instance.clearIndex();
                return this;
            }

            /**
             * <code>bytes value = 2;</code>
             * @return The value.
             */
            @java.lang.Override
            public com.google.protobuf.ByteString getValue() {
                return instance.getValue();
            }
            /**
             * <code>bytes value = 2;</code>
             * @param value The value to set.
             * @return This builder for chaining.
             */
            public Builder setValue(com.google.protobuf.ByteString value) {
                copyOnWrite();
                instance.setValue(value);
                return this;
            }
            /**
             * <code>bytes value = 2;</code>
             * @return This builder for chaining.
             */
            public Builder clearValue() {
                copyOnWrite();
                instance.clearValue();
                return this;
            }

            // @@protoc_insertion_point(builder_scope:rm_local_ctrl.PropertyValue)
        }
        @java.lang.Override
        @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
        protected final java.lang.Object dynamicMethod(
                com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
                java.lang.Object arg0, java.lang.Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE: {
                    return new com.espressif.EspLocalCtrl.PropertyValue();
                }
                case NEW_BUILDER: {
                    return new Builder();
                }
                case BUILD_MESSAGE_INFO: {
                    java.lang.Object[] objects = new java.lang.Object[] {
                            "index_",
                            "value_",
                    };
                    java.lang.String info =
                            "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\n" +
                                    "";
                    return newMessageInfo(DEFAULT_INSTANCE, info, objects);
                }
                // fall through
                case GET_DEFAULT_INSTANCE: {
                    return DEFAULT_INSTANCE;
                }
                case GET_PARSER: {
                    com.google.protobuf.Parser<com.espressif.EspLocalCtrl.PropertyValue> parser = PARSER;
                    if (parser == null) {
                        synchronized (com.espressif.EspLocalCtrl.PropertyValue.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser =
                                        new DefaultInstanceBasedParser<com.espressif.EspLocalCtrl.PropertyValue>(
                                                DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                }
                case GET_MEMOIZED_IS_INITIALIZED: {
                    return (byte) 1;
                }
                case SET_MEMOIZED_IS_INITIALIZED: {
                    return null;
                }
            }
            throw new UnsupportedOperationException();
        }


        // @@protoc_insertion_point(class_scope:rm_local_ctrl.PropertyValue)
        private static final com.espressif.EspLocalCtrl.PropertyValue DEFAULT_INSTANCE;
        static {
            PropertyValue defaultInstance = new PropertyValue();
            // New instances are implicitly immutable so no need to make
            // immutable.
            DEFAULT_INSTANCE = defaultInstance;
            com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
                    PropertyValue.class, defaultInstance);
        }

        public static com.espressif.EspLocalCtrl.PropertyValue getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static volatile com.google.protobuf.Parser<PropertyValue> PARSER;

        public static com.google.protobuf.Parser<PropertyValue> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface CmdSetPropertyValuesOrBuilder extends
            // @@protoc_insertion_point(interface_extends:rm_local_ctrl.CmdSetPropertyValues)
            com.google.protobuf.MessageLiteOrBuilder {

        /**
         * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
         */
        java.util.List<com.espressif.EspLocalCtrl.PropertyValue>
        getPropsList();
        /**
         * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
         */
        com.espressif.EspLocalCtrl.PropertyValue getProps(int index);
        /**
         * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
         */
        int getPropsCount();
    }
    /**
     * Protobuf type {@code rm_local_ctrl.CmdSetPropertyValues}
     */
    public  static final class CmdSetPropertyValues extends
            com.google.protobuf.GeneratedMessageLite<
                    CmdSetPropertyValues, CmdSetPropertyValues.Builder> implements
            // @@protoc_insertion_point(message_implements:rm_local_ctrl.CmdSetPropertyValues)
            CmdSetPropertyValuesOrBuilder {
        private CmdSetPropertyValues() {
            props_ = emptyProtobufList();
        }
        public static final int PROPS_FIELD_NUMBER = 1;
        private com.google.protobuf.Internal.ProtobufList<com.espressif.EspLocalCtrl.PropertyValue> props_;
        /**
         * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
         */
        @java.lang.Override
        public java.util.List<com.espressif.EspLocalCtrl.PropertyValue> getPropsList() {
            return props_;
        }
        /**
         * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
         */
        public java.util.List<? extends com.espressif.EspLocalCtrl.PropertyValueOrBuilder>
        getPropsOrBuilderList() {
            return props_;
        }
        /**
         * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
         */
        @java.lang.Override
        public int getPropsCount() {
            return props_.size();
        }
        /**
         * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
         */
        @java.lang.Override
        public com.espressif.EspLocalCtrl.PropertyValue getProps(int index) {
            return props_.get(index);
        }
        /**
         * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
         */
        public com.espressif.EspLocalCtrl.PropertyValueOrBuilder getPropsOrBuilder(
                int index) {
            return props_.get(index);
        }
        private void ensurePropsIsMutable() {
            com.google.protobuf.Internal.ProtobufList<com.espressif.EspLocalCtrl.PropertyValue> tmp = props_;
            if (!tmp.isModifiable()) {
                props_ =
                        com.google.protobuf.GeneratedMessageLite.mutableCopy(tmp);
            }
        }

        /**
         * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
         */
        private void setProps(
                int index, com.espressif.EspLocalCtrl.PropertyValue value) {
            value.getClass();
            ensurePropsIsMutable();
            props_.set(index, value);
        }
        /**
         * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
         */
        private void addProps(com.espressif.EspLocalCtrl.PropertyValue value) {
            value.getClass();
            ensurePropsIsMutable();
            props_.add(value);
        }
        /**
         * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
         */
        private void addProps(
                int index, com.espressif.EspLocalCtrl.PropertyValue value) {
            value.getClass();
            ensurePropsIsMutable();
            props_.add(index, value);
        }
        /**
         * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
         */
        private void addAllProps(
                java.lang.Iterable<? extends com.espressif.EspLocalCtrl.PropertyValue> values) {
            ensurePropsIsMutable();
            com.google.protobuf.AbstractMessageLite.addAll(
                    values, props_);
        }
        /**
         * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
         */
        private void clearProps() {
            props_ = emptyProtobufList();
        }
        /**
         * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
         */
        private void removeProps(int index) {
            ensurePropsIsMutable();
            props_.remove(index);
        }

        public static com.espressif.EspLocalCtrl.CmdSetPropertyValues parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.CmdSetPropertyValues parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.CmdSetPropertyValues parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.CmdSetPropertyValues parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.CmdSetPropertyValues parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.CmdSetPropertyValues parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.CmdSetPropertyValues parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.CmdSetPropertyValues parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.CmdSetPropertyValues parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.CmdSetPropertyValues parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.CmdSetPropertyValues parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.CmdSetPropertyValues parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }
        public static Builder newBuilder(com.espressif.EspLocalCtrl.CmdSetPropertyValues prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /**
         * Protobuf type {@code rm_local_ctrl.CmdSetPropertyValues}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageLite.Builder<
                        com.espressif.EspLocalCtrl.CmdSetPropertyValues, Builder> implements
                // @@protoc_insertion_point(builder_implements:rm_local_ctrl.CmdSetPropertyValues)
                com.espressif.EspLocalCtrl.CmdSetPropertyValuesOrBuilder {
            // Construct using rm_local_ctrl.EspLocalCtrl.CmdSetPropertyValues.newBuilder()
            private Builder() {
                super(DEFAULT_INSTANCE);
            }


            /**
             * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
             */
            @java.lang.Override
            public java.util.List<com.espressif.EspLocalCtrl.PropertyValue> getPropsList() {
                return java.util.Collections.unmodifiableList(
                        instance.getPropsList());
            }
            /**
             * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
             */
            @java.lang.Override
            public int getPropsCount() {
                return instance.getPropsCount();
            }/**
             * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
             */
            @java.lang.Override
            public com.espressif.EspLocalCtrl.PropertyValue getProps(int index) {
                return instance.getProps(index);
            }
            /**
             * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
             */
            public Builder setProps(
                    int index, com.espressif.EspLocalCtrl.PropertyValue value) {
                copyOnWrite();
                instance.setProps(index, value);
                return this;
            }
            /**
             * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
             */
            public Builder setProps(
                    int index, com.espressif.EspLocalCtrl.PropertyValue.Builder builderForValue) {
                copyOnWrite();
                instance.setProps(index,
                        builderForValue.build());
                return this;
            }
            /**
             * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
             */
            public Builder addProps(com.espressif.EspLocalCtrl.PropertyValue value) {
                copyOnWrite();
                instance.addProps(value);
                return this;
            }
            /**
             * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
             */
            public Builder addProps(
                    int index, com.espressif.EspLocalCtrl.PropertyValue value) {
                copyOnWrite();
                instance.addProps(index, value);
                return this;
            }
            /**
             * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
             */
            public Builder addProps(
                    com.espressif.EspLocalCtrl.PropertyValue.Builder builderForValue) {
                copyOnWrite();
                instance.addProps(builderForValue.build());
                return this;
            }
            /**
             * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
             */
            public Builder addProps(
                    int index, com.espressif.EspLocalCtrl.PropertyValue.Builder builderForValue) {
                copyOnWrite();
                instance.addProps(index,
                        builderForValue.build());
                return this;
            }
            /**
             * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
             */
            public Builder addAllProps(
                    java.lang.Iterable<? extends com.espressif.EspLocalCtrl.PropertyValue> values) {
                copyOnWrite();
                instance.addAllProps(values);
                return this;
            }
            /**
             * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
             */
            public Builder clearProps() {
                copyOnWrite();
                instance.clearProps();
                return this;
            }
            /**
             * <code>repeated .rm_local_ctrl.PropertyValue props = 1;</code>
             */
            public Builder removeProps(int index) {
                copyOnWrite();
                instance.removeProps(index);
                return this;
            }

            // @@protoc_insertion_point(builder_scope:rm_local_ctrl.CmdSetPropertyValues)
        }
        @java.lang.Override
        @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
        protected final java.lang.Object dynamicMethod(
                com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
                java.lang.Object arg0, java.lang.Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE: {
                    return new com.espressif.EspLocalCtrl.CmdSetPropertyValues();
                }
                case NEW_BUILDER: {
                    return new Builder();
                }
                case BUILD_MESSAGE_INFO: {
                    java.lang.Object[] objects = new java.lang.Object[] {
                            "props_",
                            com.espressif.EspLocalCtrl.PropertyValue.class,
                    };
                    java.lang.String info =
                            "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b";
                    return newMessageInfo(DEFAULT_INSTANCE, info, objects);
                }
                // fall through
                case GET_DEFAULT_INSTANCE: {
                    return DEFAULT_INSTANCE;
                }
                case GET_PARSER: {
                    com.google.protobuf.Parser<com.espressif.EspLocalCtrl.CmdSetPropertyValues> parser = PARSER;
                    if (parser == null) {
                        synchronized (com.espressif.EspLocalCtrl.CmdSetPropertyValues.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser =
                                        new DefaultInstanceBasedParser<com.espressif.EspLocalCtrl.CmdSetPropertyValues>(
                                                DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                }
                case GET_MEMOIZED_IS_INITIALIZED: {
                    return (byte) 1;
                }
                case SET_MEMOIZED_IS_INITIALIZED: {
                    return null;
                }
            }
            throw new UnsupportedOperationException();
        }


        // @@protoc_insertion_point(class_scope:rm_local_ctrl.CmdSetPropertyValues)
        private static final com.espressif.EspLocalCtrl.CmdSetPropertyValues DEFAULT_INSTANCE;
        static {
            CmdSetPropertyValues defaultInstance = new CmdSetPropertyValues();
            // New instances are implicitly immutable so no need to make
            // immutable.
            DEFAULT_INSTANCE = defaultInstance;
            com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
                    CmdSetPropertyValues.class, defaultInstance);
        }

        public static com.espressif.EspLocalCtrl.CmdSetPropertyValues getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static volatile com.google.protobuf.Parser<CmdSetPropertyValues> PARSER;

        public static com.google.protobuf.Parser<CmdSetPropertyValues> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface RespSetPropertyValuesOrBuilder extends
            // @@protoc_insertion_point(interface_extends:rm_local_ctrl.RespSetPropertyValues)
            com.google.protobuf.MessageLiteOrBuilder {

        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @return The enum numeric value on the wire for status.
         */
        int getStatusValue();
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @return The status.
         */
        Constants.Status getStatus();
    }
    /**
     * Protobuf type {@code rm_local_ctrl.RespSetPropertyValues}
     */
    public  static final class RespSetPropertyValues extends
            com.google.protobuf.GeneratedMessageLite<
                    RespSetPropertyValues, RespSetPropertyValues.Builder> implements
            // @@protoc_insertion_point(message_implements:rm_local_ctrl.RespSetPropertyValues)
            RespSetPropertyValuesOrBuilder {
        private RespSetPropertyValues() {
        }
        public static final int STATUS_FIELD_NUMBER = 1;
        private int status_;
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @return The enum numeric value on the wire for status.
         */
        @java.lang.Override
        public int getStatusValue() {
            return status_;
        }
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @return The status.
         */
        @java.lang.Override
        public Constants.Status getStatus() {
            Constants.Status result = Constants.Status.forNumber(status_);
            return result == null ? Constants.Status.UNRECOGNIZED : result;
        }
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @param value The enum numeric value on the wire for status to set.
         */
        private void setStatusValue(int value) {
            status_ = value;
        }
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         * @param value The status to set.
         */
        private void setStatus(Constants.Status value) {
            status_ = value.getNumber();

        }
        /**
         * <code>.rm_local_ctrl.Status status = 1;</code>
         */
        private void clearStatus() {

            status_ = 0;
        }

        public static com.espressif.EspLocalCtrl.RespSetPropertyValues parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.RespSetPropertyValues parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.RespSetPropertyValues parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.RespSetPropertyValues parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.RespSetPropertyValues parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.RespSetPropertyValues parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.RespSetPropertyValues parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.RespSetPropertyValues parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.RespSetPropertyValues parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.RespSetPropertyValues parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.RespSetPropertyValues parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.RespSetPropertyValues parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }
        public static Builder newBuilder(com.espressif.EspLocalCtrl.RespSetPropertyValues prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /**
         * Protobuf type {@code rm_local_ctrl.RespSetPropertyValues}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageLite.Builder<
                        com.espressif.EspLocalCtrl.RespSetPropertyValues, Builder> implements
                // @@protoc_insertion_point(builder_implements:rm_local_ctrl.RespSetPropertyValues)
                com.espressif.EspLocalCtrl.RespSetPropertyValuesOrBuilder {
            // Construct using rm_local_ctrl.EspLocalCtrl.RespSetPropertyValues.newBuilder()
            private Builder() {
                super(DEFAULT_INSTANCE);
            }


            /**
             * <code>.rm_local_ctrl.Status status = 1;</code>
             * @return The enum numeric value on the wire for status.
             */
            @java.lang.Override
            public int getStatusValue() {
                return instance.getStatusValue();
            }
            /**
             * <code>.rm_local_ctrl.Status status = 1;</code>
             * @param value The status to set.
             * @return This builder for chaining.
             */
            public Builder setStatusValue(int value) {
                copyOnWrite();
                instance.setStatusValue(value);
                return this;
            }
            /**
             * <code>.rm_local_ctrl.Status status = 1;</code>
             * @return The status.
             */
            @java.lang.Override
            public Constants.Status getStatus() {
                return instance.getStatus();
            }
            /**
             * <code>.rm_local_ctrl.Status status = 1;</code>
             * @param value The enum numeric value on the wire for status to set.
             * @return This builder for chaining.
             */
            public Builder setStatus(Constants.Status value) {
                copyOnWrite();
                instance.setStatus(value);
                return this;
            }
            /**
             * <code>.rm_local_ctrl.Status status = 1;</code>
             * @return This builder for chaining.
             */
            public Builder clearStatus() {
                copyOnWrite();
                instance.clearStatus();
                return this;
            }

            // @@protoc_insertion_point(builder_scope:rm_local_ctrl.RespSetPropertyValues)
        }
        @java.lang.Override
        @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
        protected final java.lang.Object dynamicMethod(
                com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
                java.lang.Object arg0, java.lang.Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE: {
                    return new com.espressif.EspLocalCtrl.RespSetPropertyValues();
                }
                case NEW_BUILDER: {
                    return new Builder();
                }
                case BUILD_MESSAGE_INFO: {
                    java.lang.Object[] objects = new java.lang.Object[] {
                            "status_",
                    };
                    java.lang.String info =
                            "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f";
                    return newMessageInfo(DEFAULT_INSTANCE, info, objects);
                }
                // fall through
                case GET_DEFAULT_INSTANCE: {
                    return DEFAULT_INSTANCE;
                }
                case GET_PARSER: {
                    com.google.protobuf.Parser<com.espressif.EspLocalCtrl.RespSetPropertyValues> parser = PARSER;
                    if (parser == null) {
                        synchronized (com.espressif.EspLocalCtrl.RespSetPropertyValues.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser =
                                        new DefaultInstanceBasedParser<com.espressif.EspLocalCtrl.RespSetPropertyValues>(
                                                DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                }
                case GET_MEMOIZED_IS_INITIALIZED: {
                    return (byte) 1;
                }
                case SET_MEMOIZED_IS_INITIALIZED: {
                    return null;
                }
            }
            throw new UnsupportedOperationException();
        }


        // @@protoc_insertion_point(class_scope:rm_local_ctrl.RespSetPropertyValues)
        private static final com.espressif.EspLocalCtrl.RespSetPropertyValues DEFAULT_INSTANCE;
        static {
            RespSetPropertyValues defaultInstance = new RespSetPropertyValues();
            // New instances are implicitly immutable so no need to make
            // immutable.
            DEFAULT_INSTANCE = defaultInstance;
            com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
                    RespSetPropertyValues.class, defaultInstance);
        }

        public static com.espressif.EspLocalCtrl.RespSetPropertyValues getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static volatile com.google.protobuf.Parser<RespSetPropertyValues> PARSER;

        public static com.google.protobuf.Parser<RespSetPropertyValues> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface LocalCtrlMessageOrBuilder extends
            // @@protoc_insertion_point(interface_extends:rm_local_ctrl.LocalCtrlMessage)
            com.google.protobuf.MessageLiteOrBuilder {

        /**
         * <code>.rm_local_ctrl.LocalCtrlMsgType msg = 1;</code>
         * @return The enum numeric value on the wire for msg.
         */
        int getMsgValue();
        /**
         * <code>.rm_local_ctrl.LocalCtrlMsgType msg = 1;</code>
         * @return The msg.
         */
        com.espressif.EspLocalCtrl.LocalCtrlMsgType getMsg();

        /**
         * <code>.rm_local_ctrl.CmdGetPropertyCount cmd_get_prop_count = 10;</code>
         * @return Whether the cmdGetPropCount field is set.
         */
        boolean hasCmdGetPropCount();
        /**
         * <code>.rm_local_ctrl.CmdGetPropertyCount cmd_get_prop_count = 10;</code>
         * @return The cmdGetPropCount.
         */
        com.espressif.EspLocalCtrl.CmdGetPropertyCount getCmdGetPropCount();

        /**
         * <code>.rm_local_ctrl.RespGetPropertyCount resp_get_prop_count = 11;</code>
         * @return Whether the respGetPropCount field is set.
         */
        boolean hasRespGetPropCount();
        /**
         * <code>.rm_local_ctrl.RespGetPropertyCount resp_get_prop_count = 11;</code>
         * @return The respGetPropCount.
         */
        com.espressif.EspLocalCtrl.RespGetPropertyCount getRespGetPropCount();

        /**
         * <code>.rm_local_ctrl.CmdGetPropertyValues cmd_get_prop_vals = 12;</code>
         * @return Whether the cmdGetPropVals field is set.
         */
        boolean hasCmdGetPropVals();
        /**
         * <code>.rm_local_ctrl.CmdGetPropertyValues cmd_get_prop_vals = 12;</code>
         * @return The cmdGetPropVals.
         */
        com.espressif.EspLocalCtrl.CmdGetPropertyValues getCmdGetPropVals();

        /**
         * <code>.rm_local_ctrl.RespGetPropertyValues resp_get_prop_vals = 13;</code>
         * @return Whether the respGetPropVals field is set.
         */
        boolean hasRespGetPropVals();
        /**
         * <code>.rm_local_ctrl.RespGetPropertyValues resp_get_prop_vals = 13;</code>
         * @return The respGetPropVals.
         */
        com.espressif.EspLocalCtrl.RespGetPropertyValues getRespGetPropVals();

        /**
         * <code>.rm_local_ctrl.CmdSetPropertyValues cmd_set_prop_vals = 14;</code>
         * @return Whether the cmdSetPropVals field is set.
         */
        boolean hasCmdSetPropVals();
        /**
         * <code>.rm_local_ctrl.CmdSetPropertyValues cmd_set_prop_vals = 14;</code>
         * @return The cmdSetPropVals.
         */
        com.espressif.EspLocalCtrl.CmdSetPropertyValues getCmdSetPropVals();

        /**
         * <code>.rm_local_ctrl.RespSetPropertyValues resp_set_prop_vals = 15;</code>
         * @return Whether the respSetPropVals field is set.
         */
        boolean hasRespSetPropVals();
        /**
         * <code>.rm_local_ctrl.RespSetPropertyValues resp_set_prop_vals = 15;</code>
         * @return The respSetPropVals.
         */
        com.espressif.EspLocalCtrl.RespSetPropertyValues getRespSetPropVals();

        public com.espressif.EspLocalCtrl.LocalCtrlMessage.PayloadCase getPayloadCase();
    }
    /**
     * Protobuf type {@code rm_local_ctrl.LocalCtrlMessage}
     */
    public  static final class LocalCtrlMessage extends
            com.google.protobuf.GeneratedMessageLite<
                    LocalCtrlMessage, LocalCtrlMessage.Builder> implements
            // @@protoc_insertion_point(message_implements:rm_local_ctrl.LocalCtrlMessage)
            LocalCtrlMessageOrBuilder {
        private LocalCtrlMessage() {
        }
        private int payloadCase_ = 0;
        private java.lang.Object payload_;
        public enum PayloadCase {
            CMD_GET_PROP_COUNT(10),
            RESP_GET_PROP_COUNT(11),
            CMD_GET_PROP_VALS(12),
            RESP_GET_PROP_VALS(13),
            CMD_SET_PROP_VALS(14),
            RESP_SET_PROP_VALS(15),
            PAYLOAD_NOT_SET(0);
            private final int value;
            private PayloadCase(int value) {
                this.value = value;
            }
            /**
             * @deprecated Use {@link #forNumber(int)} instead.
             */
            @java.lang.Deprecated
            public static PayloadCase valueOf(int value) {
                return forNumber(value);
            }

            public static PayloadCase forNumber(int value) {
                switch (value) {
                    case 10: return CMD_GET_PROP_COUNT;
                    case 11: return RESP_GET_PROP_COUNT;
                    case 12: return CMD_GET_PROP_VALS;
                    case 13: return RESP_GET_PROP_VALS;
                    case 14: return CMD_SET_PROP_VALS;
                    case 15: return RESP_SET_PROP_VALS;
                    case 0: return PAYLOAD_NOT_SET;
                    default: return null;
                }
            }
            public int getNumber() {
                return this.value;
            }
        };

        @java.lang.Override
        public PayloadCase
        getPayloadCase() {
            return PayloadCase.forNumber(
                    payloadCase_);
        }

        private void clearPayload() {
            payloadCase_ = 0;
            payload_ = null;
        }

        public static final int MSG_FIELD_NUMBER = 1;
        private int msg_;
        /**
         * <code>.rm_local_ctrl.LocalCtrlMsgType msg = 1;</code>
         * @return The enum numeric value on the wire for msg.
         */
        @java.lang.Override
        public int getMsgValue() {
            return msg_;
        }
        /**
         * <code>.rm_local_ctrl.LocalCtrlMsgType msg = 1;</code>
         * @return The msg.
         */
        @java.lang.Override
        public com.espressif.EspLocalCtrl.LocalCtrlMsgType getMsg() {
            com.espressif.EspLocalCtrl.LocalCtrlMsgType result = com.espressif.EspLocalCtrl.LocalCtrlMsgType.forNumber(msg_);
            return result == null ? com.espressif.EspLocalCtrl.LocalCtrlMsgType.UNRECOGNIZED : result;
        }
        /**
         * <code>.rm_local_ctrl.LocalCtrlMsgType msg = 1;</code>
         * @param value The enum numeric value on the wire for msg to set.
         */
        private void setMsgValue(int value) {
            msg_ = value;
        }
        /**
         * <code>.rm_local_ctrl.LocalCtrlMsgType msg = 1;</code>
         * @param value The msg to set.
         */
        private void setMsg(com.espressif.EspLocalCtrl.LocalCtrlMsgType value) {
            msg_ = value.getNumber();

        }
        /**
         * <code>.rm_local_ctrl.LocalCtrlMsgType msg = 1;</code>
         */
        private void clearMsg() {

            msg_ = 0;
        }

        public static final int CMD_GET_PROP_COUNT_FIELD_NUMBER = 10;
        /**
         * <code>.rm_local_ctrl.CmdGetPropertyCount cmd_get_prop_count = 10;</code>
         */
        @java.lang.Override
        public boolean hasCmdGetPropCount() {
            return payloadCase_ == 10;
        }
        /**
         * <code>.rm_local_ctrl.CmdGetPropertyCount cmd_get_prop_count = 10;</code>
         */
        @java.lang.Override
        public com.espressif.EspLocalCtrl.CmdGetPropertyCount getCmdGetPropCount() {
            if (payloadCase_ == 10) {
                return (com.espressif.EspLocalCtrl.CmdGetPropertyCount) payload_;
            }
            return com.espressif.EspLocalCtrl.CmdGetPropertyCount.getDefaultInstance();
        }
        /**
         * <code>.rm_local_ctrl.CmdGetPropertyCount cmd_get_prop_count = 10;</code>
         */
        private void setCmdGetPropCount(com.espressif.EspLocalCtrl.CmdGetPropertyCount value) {
            value.getClass();
            payload_ = value;
            payloadCase_ = 10;
        }
        /**
         * <code>.rm_local_ctrl.CmdGetPropertyCount cmd_get_prop_count = 10;</code>
         */
        private void mergeCmdGetPropCount(com.espressif.EspLocalCtrl.CmdGetPropertyCount value) {
            value.getClass();
            if (payloadCase_ == 10 &&
                    payload_ != com.espressif.EspLocalCtrl.CmdGetPropertyCount.getDefaultInstance()) {
                payload_ = com.espressif.EspLocalCtrl.CmdGetPropertyCount.newBuilder((com.espressif.EspLocalCtrl.CmdGetPropertyCount) payload_)
                        .mergeFrom(value).buildPartial();
            } else {
                payload_ = value;
            }
            payloadCase_ = 10;
        }
        /**
         * <code>.rm_local_ctrl.CmdGetPropertyCount cmd_get_prop_count = 10;</code>
         */
        private void clearCmdGetPropCount() {
            if (payloadCase_ == 10) {
                payloadCase_ = 0;
                payload_ = null;
            }
        }

        public static final int RESP_GET_PROP_COUNT_FIELD_NUMBER = 11;
        /**
         * <code>.rm_local_ctrl.RespGetPropertyCount resp_get_prop_count = 11;</code>
         */
        @java.lang.Override
        public boolean hasRespGetPropCount() {
            return payloadCase_ == 11;
        }
        /**
         * <code>.rm_local_ctrl.RespGetPropertyCount resp_get_prop_count = 11;</code>
         */
        @java.lang.Override
        public com.espressif.EspLocalCtrl.RespGetPropertyCount getRespGetPropCount() {
            if (payloadCase_ == 11) {
                return (com.espressif.EspLocalCtrl.RespGetPropertyCount) payload_;
            }
            return com.espressif.EspLocalCtrl.RespGetPropertyCount.getDefaultInstance();
        }
        /**
         * <code>.rm_local_ctrl.RespGetPropertyCount resp_get_prop_count = 11;</code>
         */
        private void setRespGetPropCount(com.espressif.EspLocalCtrl.RespGetPropertyCount value) {
            value.getClass();
            payload_ = value;
            payloadCase_ = 11;
        }
        /**
         * <code>.rm_local_ctrl.RespGetPropertyCount resp_get_prop_count = 11;</code>
         */
        private void mergeRespGetPropCount(com.espressif.EspLocalCtrl.RespGetPropertyCount value) {
            value.getClass();
            if (payloadCase_ == 11 &&
                    payload_ != com.espressif.EspLocalCtrl.RespGetPropertyCount.getDefaultInstance()) {
                payload_ = com.espressif.EspLocalCtrl.RespGetPropertyCount.newBuilder((com.espressif.EspLocalCtrl.RespGetPropertyCount) payload_)
                        .mergeFrom(value).buildPartial();
            } else {
                payload_ = value;
            }
            payloadCase_ = 11;
        }
        /**
         * <code>.rm_local_ctrl.RespGetPropertyCount resp_get_prop_count = 11;</code>
         */
        private void clearRespGetPropCount() {
            if (payloadCase_ == 11) {
                payloadCase_ = 0;
                payload_ = null;
            }
        }

        public static final int CMD_GET_PROP_VALS_FIELD_NUMBER = 12;
        /**
         * <code>.rm_local_ctrl.CmdGetPropertyValues cmd_get_prop_vals = 12;</code>
         */
        @java.lang.Override
        public boolean hasCmdGetPropVals() {
            return payloadCase_ == 12;
        }
        /**
         * <code>.rm_local_ctrl.CmdGetPropertyValues cmd_get_prop_vals = 12;</code>
         */
        @java.lang.Override
        public com.espressif.EspLocalCtrl.CmdGetPropertyValues getCmdGetPropVals() {
            if (payloadCase_ == 12) {
                return (com.espressif.EspLocalCtrl.CmdGetPropertyValues) payload_;
            }
            return com.espressif.EspLocalCtrl.CmdGetPropertyValues.getDefaultInstance();
        }
        /**
         * <code>.rm_local_ctrl.CmdGetPropertyValues cmd_get_prop_vals = 12;</code>
         */
        private void setCmdGetPropVals(com.espressif.EspLocalCtrl.CmdGetPropertyValues value) {
            value.getClass();
            payload_ = value;
            payloadCase_ = 12;
        }
        /**
         * <code>.rm_local_ctrl.CmdGetPropertyValues cmd_get_prop_vals = 12;</code>
         */
        private void mergeCmdGetPropVals(com.espressif.EspLocalCtrl.CmdGetPropertyValues value) {
            value.getClass();
            if (payloadCase_ == 12 &&
                    payload_ != com.espressif.EspLocalCtrl.CmdGetPropertyValues.getDefaultInstance()) {
                payload_ = com.espressif.EspLocalCtrl.CmdGetPropertyValues.newBuilder((com.espressif.EspLocalCtrl.CmdGetPropertyValues) payload_)
                        .mergeFrom(value).buildPartial();
            } else {
                payload_ = value;
            }
            payloadCase_ = 12;
        }
        /**
         * <code>.rm_local_ctrl.CmdGetPropertyValues cmd_get_prop_vals = 12;</code>
         */
        private void clearCmdGetPropVals() {
            if (payloadCase_ == 12) {
                payloadCase_ = 0;
                payload_ = null;
            }
        }

        public static final int RESP_GET_PROP_VALS_FIELD_NUMBER = 13;
        /**
         * <code>.rm_local_ctrl.RespGetPropertyValues resp_get_prop_vals = 13;</code>
         */
        @java.lang.Override
        public boolean hasRespGetPropVals() {
            return payloadCase_ == 13;
        }
        /**
         * <code>.rm_local_ctrl.RespGetPropertyValues resp_get_prop_vals = 13;</code>
         */
        @java.lang.Override
        public com.espressif.EspLocalCtrl.RespGetPropertyValues getRespGetPropVals() {
            if (payloadCase_ == 13) {
                return (com.espressif.EspLocalCtrl.RespGetPropertyValues) payload_;
            }
            return com.espressif.EspLocalCtrl.RespGetPropertyValues.getDefaultInstance();
        }
        /**
         * <code>.rm_local_ctrl.RespGetPropertyValues resp_get_prop_vals = 13;</code>
         */
        private void setRespGetPropVals(com.espressif.EspLocalCtrl.RespGetPropertyValues value) {
            value.getClass();
            payload_ = value;
            payloadCase_ = 13;
        }
        /**
         * <code>.rm_local_ctrl.RespGetPropertyValues resp_get_prop_vals = 13;</code>
         */
        private void mergeRespGetPropVals(com.espressif.EspLocalCtrl.RespGetPropertyValues value) {
            value.getClass();
            if (payloadCase_ == 13 &&
                    payload_ != com.espressif.EspLocalCtrl.RespGetPropertyValues.getDefaultInstance()) {
                payload_ = com.espressif.EspLocalCtrl.RespGetPropertyValues.newBuilder((com.espressif.EspLocalCtrl.RespGetPropertyValues) payload_)
                        .mergeFrom(value).buildPartial();
            } else {
                payload_ = value;
            }
            payloadCase_ = 13;
        }
        /**
         * <code>.rm_local_ctrl.RespGetPropertyValues resp_get_prop_vals = 13;</code>
         */
        private void clearRespGetPropVals() {
            if (payloadCase_ == 13) {
                payloadCase_ = 0;
                payload_ = null;
            }
        }

        public static final int CMD_SET_PROP_VALS_FIELD_NUMBER = 14;
        /**
         * <code>.rm_local_ctrl.CmdSetPropertyValues cmd_set_prop_vals = 14;</code>
         */
        @java.lang.Override
        public boolean hasCmdSetPropVals() {
            return payloadCase_ == 14;
        }
        /**
         * <code>.rm_local_ctrl.CmdSetPropertyValues cmd_set_prop_vals = 14;</code>
         */
        @java.lang.Override
        public com.espressif.EspLocalCtrl.CmdSetPropertyValues getCmdSetPropVals() {
            if (payloadCase_ == 14) {
                return (com.espressif.EspLocalCtrl.CmdSetPropertyValues) payload_;
            }
            return com.espressif.EspLocalCtrl.CmdSetPropertyValues.getDefaultInstance();
        }
        /**
         * <code>.rm_local_ctrl.CmdSetPropertyValues cmd_set_prop_vals = 14;</code>
         */
        private void setCmdSetPropVals(com.espressif.EspLocalCtrl.CmdSetPropertyValues value) {
            value.getClass();
            payload_ = value;
            payloadCase_ = 14;
        }
        /**
         * <code>.rm_local_ctrl.CmdSetPropertyValues cmd_set_prop_vals = 14;</code>
         */
        private void mergeCmdSetPropVals(com.espressif.EspLocalCtrl.CmdSetPropertyValues value) {
            value.getClass();
            if (payloadCase_ == 14 &&
                    payload_ != com.espressif.EspLocalCtrl.CmdSetPropertyValues.getDefaultInstance()) {
                payload_ = com.espressif.EspLocalCtrl.CmdSetPropertyValues.newBuilder((com.espressif.EspLocalCtrl.CmdSetPropertyValues) payload_)
                        .mergeFrom(value).buildPartial();
            } else {
                payload_ = value;
            }
            payloadCase_ = 14;
        }
        /**
         * <code>.rm_local_ctrl.CmdSetPropertyValues cmd_set_prop_vals = 14;</code>
         */
        private void clearCmdSetPropVals() {
            if (payloadCase_ == 14) {
                payloadCase_ = 0;
                payload_ = null;
            }
        }

        public static final int RESP_SET_PROP_VALS_FIELD_NUMBER = 15;
        /**
         * <code>.rm_local_ctrl.RespSetPropertyValues resp_set_prop_vals = 15;</code>
         */
        @java.lang.Override
        public boolean hasRespSetPropVals() {
            return payloadCase_ == 15;
        }
        /**
         * <code>.rm_local_ctrl.RespSetPropertyValues resp_set_prop_vals = 15;</code>
         */
        @java.lang.Override
        public com.espressif.EspLocalCtrl.RespSetPropertyValues getRespSetPropVals() {
            if (payloadCase_ == 15) {
                return (com.espressif.EspLocalCtrl.RespSetPropertyValues) payload_;
            }
            return com.espressif.EspLocalCtrl.RespSetPropertyValues.getDefaultInstance();
        }
        /**
         * <code>.rm_local_ctrl.RespSetPropertyValues resp_set_prop_vals = 15;</code>
         */
        private void setRespSetPropVals(com.espressif.EspLocalCtrl.RespSetPropertyValues value) {
            value.getClass();
            payload_ = value;
            payloadCase_ = 15;
        }
        /**
         * <code>.rm_local_ctrl.RespSetPropertyValues resp_set_prop_vals = 15;</code>
         */
        private void mergeRespSetPropVals(com.espressif.EspLocalCtrl.RespSetPropertyValues value) {
            value.getClass();
            if (payloadCase_ == 15 &&
                    payload_ != com.espressif.EspLocalCtrl.RespSetPropertyValues.getDefaultInstance()) {
                payload_ = com.espressif.EspLocalCtrl.RespSetPropertyValues.newBuilder((com.espressif.EspLocalCtrl.RespSetPropertyValues) payload_)
                        .mergeFrom(value).buildPartial();
            } else {
                payload_ = value;
            }
            payloadCase_ = 15;
        }
        /**
         * <code>.rm_local_ctrl.RespSetPropertyValues resp_set_prop_vals = 15;</code>
         */
        private void clearRespSetPropVals() {
            if (payloadCase_ == 15) {
                payloadCase_ = 0;
                payload_ = null;
            }
        }

        public static com.espressif.EspLocalCtrl.LocalCtrlMessage parseFrom(
                java.nio.ByteBuffer data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.LocalCtrlMessage parseFrom(
                java.nio.ByteBuffer data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.LocalCtrlMessage parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.LocalCtrlMessage parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.LocalCtrlMessage parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data);
        }
        public static com.espressif.EspLocalCtrl.LocalCtrlMessage parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, data, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.LocalCtrlMessage parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.LocalCtrlMessage parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.LocalCtrlMessage parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.LocalCtrlMessage parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
        }
        public static com.espressif.EspLocalCtrl.LocalCtrlMessage parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input);
        }
        public static com.espressif.EspLocalCtrl.LocalCtrlMessage parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageLite.parseFrom(
                    DEFAULT_INSTANCE, input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }
        public static Builder newBuilder(com.espressif.EspLocalCtrl.LocalCtrlMessage prototype) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(prototype);
        }

        /**
         * Protobuf type {@code rm_local_ctrl.LocalCtrlMessage}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageLite.Builder<
                        com.espressif.EspLocalCtrl.LocalCtrlMessage, Builder> implements
                // @@protoc_insertion_point(builder_implements:rm_local_ctrl.LocalCtrlMessage)
                com.espressif.EspLocalCtrl.LocalCtrlMessageOrBuilder {
            // Construct using rm_local_ctrl.EspLocalCtrl.LocalCtrlMessage.newBuilder()
            private Builder() {
                super(DEFAULT_INSTANCE);
            }

            @java.lang.Override
            public PayloadCase
            getPayloadCase() {
                return instance.getPayloadCase();
            }

            public Builder clearPayload() {
                copyOnWrite();
                instance.clearPayload();
                return this;
            }


            /**
             * <code>.rm_local_ctrl.LocalCtrlMsgType msg = 1;</code>
             * @return The enum numeric value on the wire for msg.
             */
            @java.lang.Override
            public int getMsgValue() {
                return instance.getMsgValue();
            }
            /**
             * <code>.rm_local_ctrl.LocalCtrlMsgType msg = 1;</code>
             * @param value The msg to set.
             * @return This builder for chaining.
             */
            public Builder setMsgValue(int value) {
                copyOnWrite();
                instance.setMsgValue(value);
                return this;
            }
            /**
             * <code>.rm_local_ctrl.LocalCtrlMsgType msg = 1;</code>
             * @return The msg.
             */
            @java.lang.Override
            public com.espressif.EspLocalCtrl.LocalCtrlMsgType getMsg() {
                return instance.getMsg();
            }
            /**
             * <code>.rm_local_ctrl.LocalCtrlMsgType msg = 1;</code>
             * @param value The enum numeric value on the wire for msg to set.
             * @return This builder for chaining.
             */
            public Builder setMsg(com.espressif.EspLocalCtrl.LocalCtrlMsgType value) {
                copyOnWrite();
                instance.setMsg(value);
                return this;
            }
            /**
             * <code>.rm_local_ctrl.LocalCtrlMsgType msg = 1;</code>
             * @return This builder for chaining.
             */
            public Builder clearMsg() {
                copyOnWrite();
                instance.clearMsg();
                return this;
            }

            /**
             * <code>.rm_local_ctrl.CmdGetPropertyCount cmd_get_prop_count = 10;</code>
             */
            @java.lang.Override
            public boolean hasCmdGetPropCount() {
                return instance.hasCmdGetPropCount();
            }
            /**
             * <code>.rm_local_ctrl.CmdGetPropertyCount cmd_get_prop_count = 10;</code>
             */
            @java.lang.Override
            public com.espressif.EspLocalCtrl.CmdGetPropertyCount getCmdGetPropCount() {
                return instance.getCmdGetPropCount();
            }
            /**
             * <code>.rm_local_ctrl.CmdGetPropertyCount cmd_get_prop_count = 10;</code>
             */
            public Builder setCmdGetPropCount(com.espressif.EspLocalCtrl.CmdGetPropertyCount value) {
                copyOnWrite();
                instance.setCmdGetPropCount(value);
                return this;
            }
            /**
             * <code>.rm_local_ctrl.CmdGetPropertyCount cmd_get_prop_count = 10;</code>
             */
            public Builder setCmdGetPropCount(
                    com.espressif.EspLocalCtrl.CmdGetPropertyCount.Builder builderForValue) {
                copyOnWrite();
                instance.setCmdGetPropCount(builderForValue.build());
                return this;
            }
            /**
             * <code>.rm_local_ctrl.CmdGetPropertyCount cmd_get_prop_count = 10;</code>
             */
            public Builder mergeCmdGetPropCount(com.espressif.EspLocalCtrl.CmdGetPropertyCount value) {
                copyOnWrite();
                instance.mergeCmdGetPropCount(value);
                return this;
            }
            /**
             * <code>.rm_local_ctrl.CmdGetPropertyCount cmd_get_prop_count = 10;</code>
             */
            public Builder clearCmdGetPropCount() {
                copyOnWrite();
                instance.clearCmdGetPropCount();
                return this;
            }

            /**
             * <code>.rm_local_ctrl.RespGetPropertyCount resp_get_prop_count = 11;</code>
             */
            @java.lang.Override
            public boolean hasRespGetPropCount() {
                return instance.hasRespGetPropCount();
            }
            /**
             * <code>.rm_local_ctrl.RespGetPropertyCount resp_get_prop_count = 11;</code>
             */
            @java.lang.Override
            public com.espressif.EspLocalCtrl.RespGetPropertyCount getRespGetPropCount() {
                return instance.getRespGetPropCount();
            }
            /**
             * <code>.rm_local_ctrl.RespGetPropertyCount resp_get_prop_count = 11;</code>
             */
            public Builder setRespGetPropCount(com.espressif.EspLocalCtrl.RespGetPropertyCount value) {
                copyOnWrite();
                instance.setRespGetPropCount(value);
                return this;
            }
            /**
             * <code>.rm_local_ctrl.RespGetPropertyCount resp_get_prop_count = 11;</code>
             */
            public Builder setRespGetPropCount(
                    com.espressif.EspLocalCtrl.RespGetPropertyCount.Builder builderForValue) {
                copyOnWrite();
                instance.setRespGetPropCount(builderForValue.build());
                return this;
            }
            /**
             * <code>.rm_local_ctrl.RespGetPropertyCount resp_get_prop_count = 11;</code>
             */
            public Builder mergeRespGetPropCount(com.espressif.EspLocalCtrl.RespGetPropertyCount value) {
                copyOnWrite();
                instance.mergeRespGetPropCount(value);
                return this;
            }
            /**
             * <code>.rm_local_ctrl.RespGetPropertyCount resp_get_prop_count = 11;</code>
             */
            public Builder clearRespGetPropCount() {
                copyOnWrite();
                instance.clearRespGetPropCount();
                return this;
            }

            /**
             * <code>.rm_local_ctrl.CmdGetPropertyValues cmd_get_prop_vals = 12;</code>
             */
            @java.lang.Override
            public boolean hasCmdGetPropVals() {
                return instance.hasCmdGetPropVals();
            }
            /**
             * <code>.rm_local_ctrl.CmdGetPropertyValues cmd_get_prop_vals = 12;</code>
             */
            @java.lang.Override
            public com.espressif.EspLocalCtrl.CmdGetPropertyValues getCmdGetPropVals() {
                return instance.getCmdGetPropVals();
            }
            /**
             * <code>.rm_local_ctrl.CmdGetPropertyValues cmd_get_prop_vals = 12;</code>
             */
            public Builder setCmdGetPropVals(com.espressif.EspLocalCtrl.CmdGetPropertyValues value) {
                copyOnWrite();
                instance.setCmdGetPropVals(value);
                return this;
            }
            /**
             * <code>.rm_local_ctrl.CmdGetPropertyValues cmd_get_prop_vals = 12;</code>
             */
            public Builder setCmdGetPropVals(
                    com.espressif.EspLocalCtrl.CmdGetPropertyValues.Builder builderForValue) {
                copyOnWrite();
                instance.setCmdGetPropVals(builderForValue.build());
                return this;
            }
            /**
             * <code>.rm_local_ctrl.CmdGetPropertyValues cmd_get_prop_vals = 12;</code>
             */
            public Builder mergeCmdGetPropVals(com.espressif.EspLocalCtrl.CmdGetPropertyValues value) {
                copyOnWrite();
                instance.mergeCmdGetPropVals(value);
                return this;
            }
            /**
             * <code>.rm_local_ctrl.CmdGetPropertyValues cmd_get_prop_vals = 12;</code>
             */
            public Builder clearCmdGetPropVals() {
                copyOnWrite();
                instance.clearCmdGetPropVals();
                return this;
            }

            /**
             * <code>.rm_local_ctrl.RespGetPropertyValues resp_get_prop_vals = 13;</code>
             */
            @java.lang.Override
            public boolean hasRespGetPropVals() {
                return instance.hasRespGetPropVals();
            }
            /**
             * <code>.rm_local_ctrl.RespGetPropertyValues resp_get_prop_vals = 13;</code>
             */
            @java.lang.Override
            public com.espressif.EspLocalCtrl.RespGetPropertyValues getRespGetPropVals() {
                return instance.getRespGetPropVals();
            }
            /**
             * <code>.rm_local_ctrl.RespGetPropertyValues resp_get_prop_vals = 13;</code>
             */
            public Builder setRespGetPropVals(com.espressif.EspLocalCtrl.RespGetPropertyValues value) {
                copyOnWrite();
                instance.setRespGetPropVals(value);
                return this;
            }
            /**
             * <code>.rm_local_ctrl.RespGetPropertyValues resp_get_prop_vals = 13;</code>
             */
            public Builder setRespGetPropVals(
                    com.espressif.EspLocalCtrl.RespGetPropertyValues.Builder builderForValue) {
                copyOnWrite();
                instance.setRespGetPropVals(builderForValue.build());
                return this;
            }
            /**
             * <code>.rm_local_ctrl.RespGetPropertyValues resp_get_prop_vals = 13;</code>
             */
            public Builder mergeRespGetPropVals(com.espressif.EspLocalCtrl.RespGetPropertyValues value) {
                copyOnWrite();
                instance.mergeRespGetPropVals(value);
                return this;
            }
            /**
             * <code>.rm_local_ctrl.RespGetPropertyValues resp_get_prop_vals = 13;</code>
             */
            public Builder clearRespGetPropVals() {
                copyOnWrite();
                instance.clearRespGetPropVals();
                return this;
            }

            /**
             * <code>.rm_local_ctrl.CmdSetPropertyValues cmd_set_prop_vals = 14;</code>
             */
            @java.lang.Override
            public boolean hasCmdSetPropVals() {
                return instance.hasCmdSetPropVals();
            }
            /**
             * <code>.rm_local_ctrl.CmdSetPropertyValues cmd_set_prop_vals = 14;</code>
             */
            @java.lang.Override
            public com.espressif.EspLocalCtrl.CmdSetPropertyValues getCmdSetPropVals() {
                return instance.getCmdSetPropVals();
            }
            /**
             * <code>.rm_local_ctrl.CmdSetPropertyValues cmd_set_prop_vals = 14;</code>
             */
            public Builder setCmdSetPropVals(com.espressif.EspLocalCtrl.CmdSetPropertyValues value) {
                copyOnWrite();
                instance.setCmdSetPropVals(value);
                return this;
            }
            /**
             * <code>.rm_local_ctrl.CmdSetPropertyValues cmd_set_prop_vals = 14;</code>
             */
            public Builder setCmdSetPropVals(
                    com.espressif.EspLocalCtrl.CmdSetPropertyValues.Builder builderForValue) {
                copyOnWrite();
                instance.setCmdSetPropVals(builderForValue.build());
                return this;
            }
            /**
             * <code>.rm_local_ctrl.CmdSetPropertyValues cmd_set_prop_vals = 14;</code>
             */
            public Builder mergeCmdSetPropVals(com.espressif.EspLocalCtrl.CmdSetPropertyValues value) {
                copyOnWrite();
                instance.mergeCmdSetPropVals(value);
                return this;
            }
            /**
             * <code>.rm_local_ctrl.CmdSetPropertyValues cmd_set_prop_vals = 14;</code>
             */
            public Builder clearCmdSetPropVals() {
                copyOnWrite();
                instance.clearCmdSetPropVals();
                return this;
            }

            /**
             * <code>.rm_local_ctrl.RespSetPropertyValues resp_set_prop_vals = 15;</code>
             */
            @java.lang.Override
            public boolean hasRespSetPropVals() {
                return instance.hasRespSetPropVals();
            }
            /**
             * <code>.rm_local_ctrl.RespSetPropertyValues resp_set_prop_vals = 15;</code>
             */
            @java.lang.Override
            public com.espressif.EspLocalCtrl.RespSetPropertyValues getRespSetPropVals() {
                return instance.getRespSetPropVals();
            }
            /**
             * <code>.rm_local_ctrl.RespSetPropertyValues resp_set_prop_vals = 15;</code>
             */
            public Builder setRespSetPropVals(com.espressif.EspLocalCtrl.RespSetPropertyValues value) {
                copyOnWrite();
                instance.setRespSetPropVals(value);
                return this;
            }
            /**
             * <code>.rm_local_ctrl.RespSetPropertyValues resp_set_prop_vals = 15;</code>
             */
            public Builder setRespSetPropVals(
                    com.espressif.EspLocalCtrl.RespSetPropertyValues.Builder builderForValue) {
                copyOnWrite();
                instance.setRespSetPropVals(builderForValue.build());
                return this;
            }
            /**
             * <code>.rm_local_ctrl.RespSetPropertyValues resp_set_prop_vals = 15;</code>
             */
            public Builder mergeRespSetPropVals(com.espressif.EspLocalCtrl.RespSetPropertyValues value) {
                copyOnWrite();
                instance.mergeRespSetPropVals(value);
                return this;
            }
            /**
             * <code>.rm_local_ctrl.RespSetPropertyValues resp_set_prop_vals = 15;</code>
             */
            public Builder clearRespSetPropVals() {
                copyOnWrite();
                instance.clearRespSetPropVals();
                return this;
            }

            // @@protoc_insertion_point(builder_scope:rm_local_ctrl.LocalCtrlMessage)
        }
        @java.lang.Override
        @java.lang.SuppressWarnings({"unchecked", "fallthrough"})
        protected final java.lang.Object dynamicMethod(
                com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
                java.lang.Object arg0, java.lang.Object arg1) {
            switch (method) {
                case NEW_MUTABLE_INSTANCE: {
                    return new com.espressif.EspLocalCtrl.LocalCtrlMessage();
                }
                case NEW_BUILDER: {
                    return new Builder();
                }
                case BUILD_MESSAGE_INFO: {
                    java.lang.Object[] objects = new java.lang.Object[] {
                            "payload_",
                            "payloadCase_",
                            "msg_",
                            com.espressif.EspLocalCtrl.CmdGetPropertyCount.class,
                            com.espressif.EspLocalCtrl.RespGetPropertyCount.class,
                            com.espressif.EspLocalCtrl.CmdGetPropertyValues.class,
                            com.espressif.EspLocalCtrl.RespGetPropertyValues.class,
                            com.espressif.EspLocalCtrl.CmdSetPropertyValues.class,
                            com.espressif.EspLocalCtrl.RespSetPropertyValues.class,
                    };
                    java.lang.String info =
                            "\u0000\u0007\u0001\u0000\u0001\u000f\u0007\u0000\u0000\u0000\u0001\f\n<\u0000\u000b" +
                                    "<\u0000\f<\u0000\r<\u0000\u000e<\u0000\u000f<\u0000";
                    return newMessageInfo(DEFAULT_INSTANCE, info, objects);
                }
                // fall through
                case GET_DEFAULT_INSTANCE: {
                    return DEFAULT_INSTANCE;
                }
                case GET_PARSER: {
                    com.google.protobuf.Parser<com.espressif.EspLocalCtrl.LocalCtrlMessage> parser = PARSER;
                    if (parser == null) {
                        synchronized (com.espressif.EspLocalCtrl.LocalCtrlMessage.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser =
                                        new DefaultInstanceBasedParser<com.espressif.EspLocalCtrl.LocalCtrlMessage>(
                                                DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                }
                case GET_MEMOIZED_IS_INITIALIZED: {
                    return (byte) 1;
                }
                case SET_MEMOIZED_IS_INITIALIZED: {
                    return null;
                }
            }
            throw new UnsupportedOperationException();
        }


        // @@protoc_insertion_point(class_scope:rm_local_ctrl.LocalCtrlMessage)
        private static final com.espressif.EspLocalCtrl.LocalCtrlMessage DEFAULT_INSTANCE;
        static {
            LocalCtrlMessage defaultInstance = new LocalCtrlMessage();
            // New instances are implicitly immutable so no need to make
            // immutable.
            DEFAULT_INSTANCE = defaultInstance;
            com.google.protobuf.GeneratedMessageLite.registerDefaultInstance(
                    LocalCtrlMessage.class, defaultInstance);
        }

        public static com.espressif.EspLocalCtrl.LocalCtrlMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static volatile com.google.protobuf.Parser<LocalCtrlMessage> PARSER;

        public static com.google.protobuf.Parser<LocalCtrlMessage> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }


    static {
    }

    // @@protoc_insertion_point(outer_class_scope)
}