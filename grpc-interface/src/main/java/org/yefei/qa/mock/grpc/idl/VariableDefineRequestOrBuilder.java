// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MockServerVariableService.proto

package org.yefei.qa.mock.grpc.idl;

public interface VariableDefineRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:mock.server.grpc.VariableDefineRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .mock.server.grpc.Variable variableList = 1;</code>
   */
  java.util.List<org.yefei.qa.mock.grpc.idl.Variable> 
      getVariableListList();
  /**
   * <code>repeated .mock.server.grpc.Variable variableList = 1;</code>
   */
  org.yefei.qa.mock.grpc.idl.Variable getVariableList(int index);
  /**
   * <code>repeated .mock.server.grpc.Variable variableList = 1;</code>
   */
  int getVariableListCount();
  /**
   * <code>repeated .mock.server.grpc.Variable variableList = 1;</code>
   */
  java.util.List<? extends org.yefei.qa.mock.grpc.idl.VariableOrBuilder> 
      getVariableListOrBuilderList();
  /**
   * <code>repeated .mock.server.grpc.Variable variableList = 1;</code>
   */
  org.yefei.qa.mock.grpc.idl.VariableOrBuilder getVariableListOrBuilder(
      int index);
}
