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
package com.hazelcast.simulator.utils;

import com.hazelcast.simulator.common.SimulatorProperties;

import java.io.File;

import static java.lang.String.format;

public class Bash {

    private final String user;
    private final String sshOptions;
    private final String scpOptions;

    public Bash(SimulatorProperties simulatorProperties) {
        this.user = simulatorProperties.getUser();
        this.sshOptions = simulatorProperties.getSshOptions();
        this.scpOptions = simulatorProperties.getScpOptions();
    }

    public String execute(String command) {
        return new BashCommand(command).execute();
    }

    public String sshTTY(String ip, String command) {
        return ssh(ip, command, false, true);
    }

    public String ssh(String ip, String command) {
        return ssh(ip, command, false, false);
    }

    public String ssh(String ip, String command, boolean throwException, boolean forceTTy) {
        String options = sshOptions + (forceTTy ? " -tt" : "");
        String sshCommand = format("ssh %s %s@%s \"%s\"", options, user, ip, command);
        return new BashCommand(sshCommand).setThrowsException(throwException).execute();
    }

    public void sshQuiet(String ip, String command) {
        ssh(ip, command + " || true");
    }

    public void killAllJavaProcesses(String ip, boolean sudo) {
        if (sudo) {
            sshQuiet(ip, "sudo killall -9 java");
        } else {
            sshQuiet(ip, "killall -9 java");
        }
    }

    public void scpToRemote(String ip, File src, String target) {
        String command = format("scp -r %s %s %s@%s:%s", scpOptions, src.getAbsolutePath(), user, ip, target);
        execute(command);
    }
}
