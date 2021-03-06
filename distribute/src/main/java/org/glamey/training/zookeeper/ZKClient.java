package org.glamey.training.zookeeper;

import org.glamey.training.zookeeper.election.Leader;

import java.util.List;

/**
 * @author zhouyang.zhou. 2017.11.07.17.
 */
public interface ZKClient {

  boolean checkExist(String path);

  void createPersistent(String path, String data) throws Exception;

  void createPersistentSeq(String path, String data) throws Exception;

  void createEphemeral(String path, String data) throws Exception;

  void createEphemeralSeq(String path, String data) throws Exception;

  String get(String path) throws Exception;

  void update(String path, String data) throws Exception;

  void del(String path) throws Exception;

  void delDoAll(String path) throws Exception;

  List<String> listChildren(String parent) throws Exception;

  List<String> listChildren(final String parent, final NodeListener nodeListener, final boolean sync) throws Exception;

  Leader createLeader(String leaderPath, String id);

  void close();
}
