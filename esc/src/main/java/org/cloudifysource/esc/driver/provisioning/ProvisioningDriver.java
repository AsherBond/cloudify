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
package org.cloudifysource.esc.driver.provisioning;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.cloudifysource.dsl.cloud.Cloud;
import org.openspaces.admin.Admin;




/*****************
 * The main interface for cloud driver implementations. All calls to scale-out/scale-in/bootstrap are executed via this interface.
 * A single instance of the implementing class will exist for each service in the cluster. An instance will also be created
 * when bootstrapping or tearing down a cloud environment.
 * 
 * @author barakme
 *
 */
public interface ProvisioningDriver {

	/**************
	 * Passes a configuration map for all setting defined for this cloud.
	 * @param cloudTemplate 
	 * @param cloud 
	 * 
	 * @param config The configuration settings.
	 */
	void setConfig(Cloud cloud, String cloudTemplate, boolean management);
	
	/**************
	 * Passes an Admin API object that can be used to query the current cluster state.
	 * IMPORTANT: do not perform any blocking operations on this Admin instance, 
	 * 
	 * @param config The configuration settings.
	 */
	void setAdmin(Admin admin);
	
	
	/***************
	 * Starts an additional machine on the cloud to scale out this specific service.
	 *   
	 * @param duration Time duration to wait for the instance.
	 * @param unit Time unit to wait for the instance.
	 * @return The details of the started instance.
	 * @throws TimeoutException In case the instance was not started in the allotted time.
	 * @throws CloudProvisioningException If a problem was encountered while starting the machine.
	 */
	MachineDetails startMachine(long duration, TimeUnit unit) throws TimeoutException, CloudProvisioningException; 

	/******************
	 * Start the management machines for this cluster.
	 * 
	 * @param duration timeout duration.
	 * @param unit timeout unit.
	 * @return The created machine details.
	 * @throws TimeoutException If creating the new machines exceeded the given timeout.
	 * @throws CloudProvisioningException If the machines needed for management could not be provisioned.
	 */
	MachineDetails[] startManagementMachines(long duration, TimeUnit unit) throws TimeoutException, CloudProvisioningException;
	
	
	/****************
	 * Stops a specific machine for scaling in or shutting down a specific service.
	 * @throws CloudProvisioningException
	 */
	boolean stopMachine(final String machineIp, final long duration, final TimeUnit unit) throws InterruptedException, TimeoutException, CloudProvisioningException;

	/*************
	 * Stops the management machines.
	 * 
	 * @throws TimeoutException in case the stop operation exceeded the given timeout.
	 * @throws CloudProvisioningException If the stop operation failed.
	 */
	void  stopManagementMachines() throws TimeoutException, CloudProvisioningException;
	
	/************
	 * Returns the name of this cloud.
	 * @return the name of the cloud.
	 */
	String getCloudName();

	/*************
	 * Called when the service that this provisioning implementation is responsible for scaling
	 * is undeployed. The implementation is expected to release/close all relevant resources,
	 * such as thread pools, sockets, files, etc.
	 */
	void close();
}
