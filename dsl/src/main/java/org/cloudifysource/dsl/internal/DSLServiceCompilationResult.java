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
package org.cloudifysource.dsl.internal;

import java.io.File;

import org.cloudifysource.dsl.cloud.Cloud;
import org.cloudifysource.dsl.Service;
import org.cloudifysource.dsl.context.ServiceContext;


public class DSLServiceCompilationResult {
	private Service service;
	private Cloud cloud;
	

	private ServiceContext context;
	private File dslFile;

	public DSLServiceCompilationResult(Service service, ServiceContext context, Cloud cloud,
			File dslFile) {
		super();
		this.service = service;
		this.context = context;
		this.dslFile = dslFile;
		this.cloud = cloud;
	}
	public DSLServiceCompilationResult(Service service, ServiceContext context,
			File dslFile) {
		this(service, context, null, dslFile);
	}

	public Service getService() {
		return service;
	}

	public ServiceContext getContext() {
		return context;
	}

	public File getDslFile() {
		return dslFile;
	}

	public Cloud getCloud() {
		return cloud;
	}

	public void setCloud(Cloud cloud) {
		this.cloud = cloud;
	}
}