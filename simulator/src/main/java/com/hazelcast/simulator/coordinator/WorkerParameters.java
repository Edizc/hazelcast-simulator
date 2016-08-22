/*
 * Copyright (c) 2008-2016, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hazelcast.simulator.coordinator;

import java.util.Map;

/**
 * Parameters for Simulator Worker. The class itself should remain implementation independent; maybe it is a Java worker,
 * maybe it is a c++ Hazelcast client worker. The implementation specific variables, can be passed using the the environment,
 * see {@link #getEnvironment()}.
 */
public class WorkerParameters {

    private final int workerStartupTimeout;
    private final String versionSpec;
    private final String workerScript;
    private final Map<String, String> environment;

    public WorkerParameters(String versionSpec,
                            int workerStartupTimeout,
                            String workerScript,
                            Map<String, String> environment) {
        this.workerStartupTimeout = workerStartupTimeout;
        this.versionSpec = versionSpec;
        this.workerScript = workerScript;
        this.environment = environment;
    }

    public Map<String, String> getEnvironment() {
        return environment;
    }

    public int getWorkerStartupTimeout() {
        return workerStartupTimeout;
    }

    public String getVersionSpec() {
        return versionSpec;
    }

    public String getWorkerScript() {
        return workerScript;
    }
}
