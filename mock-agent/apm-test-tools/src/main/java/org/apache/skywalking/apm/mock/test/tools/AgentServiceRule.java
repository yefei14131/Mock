/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */


package org.apache.skywalking.apm.mock.test.tools;

import org.apache.skywalking.apm.mock.agent.core.boot.BootService;
import org.apache.skywalking.apm.mock.agent.core.boot.ServiceManager;
import org.apache.skywalking.apm.mock.agent.core.conf.Config;
import org.apache.skywalking.apm.mock.agent.core.logging.core.LogLevel;
import org.apache.skywalking.apm.mock.agent.core.plugin.loader.AgentClassLoader;
import org.apache.skywalking.apm.mock.test.helper.FieldSetter;
import org.junit.rules.ExternalResource;

import java.util.HashMap;

public class AgentServiceRule extends ExternalResource {

    @Override
    protected void after() {
        super.after();
        try {
            FieldSetter.setValue(ServiceManager.INSTANCE.getClass(), "bootedServices", new HashMap<Class, BootService>());
            ServiceManager.INSTANCE.shutdown();
        } catch (Exception e) {
        }
    }

    @Override
    protected void before() throws Throwable {
        super.before();
        AgentClassLoader.initDefaultLoader();
        Config.Logging.LEVEL = LogLevel.OFF;
        ServiceManager.INSTANCE.boot();
    }
}
