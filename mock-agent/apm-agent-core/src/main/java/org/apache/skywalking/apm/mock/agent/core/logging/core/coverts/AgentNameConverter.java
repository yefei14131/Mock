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


package org.apache.skywalking.apm.mock.agent.core.logging.core.coverts;

import org.apache.skywalking.apm.mock.agent.core.conf.Config;
import org.apache.skywalking.apm.mock.agent.core.logging.core.Converter;
import org.apache.skywalking.apm.mock.agent.core.logging.core.LogEvent;
import org.apache.skywalking.apm.mock.agent.core.conf.Config;
import org.apache.skywalking.apm.mock.agent.core.logging.core.LogEvent;

/**
 * @author alvin
 */
public class AgentNameConverter implements Converter {
    @Override
    public String convert(LogEvent logEvent) {
        return Config.Agent.SERVICE_NAME;
    }
}
