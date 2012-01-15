/*******************************************************************************
 * Copyright (c) 2011 GigaSpaces Technologies Ltd. All rights reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package org.cloudifysource.esc.util;

import java.io.OutputStream;
import java.util.logging.Logger;

public class LoggerOutputStream extends OutputStream {
    
    private String prefix = "";
    private final Logger logger;

    private StringBuilder sb;

    public LoggerOutputStream(Logger logger) {
        this.logger = logger;
        sb = new StringBuilder();
    }

    @Override
    public void write(int b) {
        char c = (char) b;
        if (c == '\n') {
            String s = sb.toString();
            logger.info(prefix + s);
            sb.setLength(0);
        } else
            sb.append(c);
    }

    @Override
    public void close()  {
        if (sb.length() > 0) {
            write('\n');
        }
    }

    @Override
    public void flush() {

    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

}