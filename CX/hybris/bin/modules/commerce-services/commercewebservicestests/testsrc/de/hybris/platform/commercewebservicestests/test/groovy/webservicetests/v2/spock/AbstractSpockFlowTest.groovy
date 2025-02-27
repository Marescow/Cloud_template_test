/*
 * Copyright (c) 2024 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.commercewebservicestests.test.groovy.webservicetests.v2.spock

import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.RESTClient
import org.junit.Ignore

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.ContentType.URLENC
import static org.apache.http.HttpStatus.SC_CREATED
import static org.apache.http.HttpStatus.SC_OK

@Ignore
abstract class AbstractSpockFlowTest extends AbstractSpockTest {

	protected static final String CUSTOMER_USERNAME = 'democustomer'
	protected static final String CUSTOMER_PASSWORD = '1234'
	protected static final String CUSTOMER_PASSWORD_STRONG = 'PAss1234!'
	protected static final String CUSTOMER_TITLE_CODE = 'dr'
	protected static final String CUSTOMER_FIRST_NAME = 'Sven'
	protected static final String CUSTOMER_LAST_NAME = 'Haiges'
	protected static final String CUSTOMER_EMAIL = 'test@sap.com'
	protected static final String CUSTOMER_ADDRESS_LINE1 = 'Nymphenburger Str. 86 - Maillingerstrasse'
	protected static final String CUSTOMER_ADDRESS_LINE2 = 'floor 2'
	protected static final String CUSTOMER_ADDRESS_POSTAL_CODE = '80331'
	protected static final String CUSTOMER_ADDRESS_PHONE = '11123234545'
	protected static final String CUSTOMER_ADDRESS_CELLPHONE = '11123234545'
	protected static final String CUSTOMER_ADDRESS_DISTRICT = 'demodistrict'
	protected static final String CUSTOMER_ADDRESS_TOWN = 'Muenchen'
	protected static final String CUSTOMER_ADDRESS_COUNTRY_ISO_CODE = 'DE'

	protected static final String CUSTOMER_MANAGER_USERNAME = 'customermanager'
	protected static final String CUSTOMER_MANAGER_PASSWORD = '1234'

	protected static final String FIELD_SET_LEVEL_BASIC = "BASIC"
	protected static final String FIELD_SET_LEVEL_DEFAULT = "DEFAULT"
	protected static final String FIELD_SET_LEVEL_FULL = "FULL"

	protected static final HEADER_TOTAL_COUNT = "X-Total-Count"

	/**
	 * This method registers customer without any kind of authorization beforehand.
	 * @param client REST client to be used
	 * @param format format to be used, defaults to JSON, does not need to be provided
	 * @return map containing id and password of registered user to be used later on
	 */
	def registerCustomer(RESTClient client, format = JSON, basePathWithSite = getBasePathWithSite()) {
		def username = System.currentTimeMillis() + '@sven.de'
		def password = CUSTOMER_PASSWORD_STRONG

		HttpResponseDecorator response = client.post(
				path: basePathWithSite + '/users',
				body: [
						'login'    : username,
						'password' : password,
						'titleCode': CUSTOMER_TITLE_CODE,
						'firstName': CUSTOMER_FIRST_NAME,
						'lastName' : CUSTOMER_LAST_NAME
				],
				contentType: format,
				requestContentType: URLENC)

		with(response) {
			if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
			status == SC_CREATED
		}
		return [
				'id'        : username,
				'password'  : password,
				'customerId': response.data.customerId
		]
	}
	/**
	 * This method creates user with given data.
	 * @param customer A map representing the customer
	 * @param client REST client to use
	 * @param format Data format to be used, defaults to JSON, does not need to be provided
	 */
	def registerCustomer(customer, RESTClient client, format = JSON, basePathWithSite = getBasePathWithSite()) {
		def username = System.currentTimeMillis() + '@sven.de'
		def password = CUSTOMER_PASSWORD

		HttpResponseDecorator response = client.post(
				path: basePathWithSite + '/users',
				body: [
						'login'    : customer.login,
						'password' : customer.password,
						'titleCode': customer.titleCode,
						'firstName': customer.firstName,
						'lastName' : customer.lastName,
						'currency' : customer.currency,
						'language' : customer.language
				],
				contentType: format,
				requestContentType: URLENC)

		with(response) {
			if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
			status == SC_CREATED
		}
	}

	/**
	 *
	 * @param client REST client to use
	 * @param user
	 * @param format Data format to be used, defaults to JSON, does not need to be provided.
	 * @param basePathWithSite base url including the site.
	 * @return address if created.
	 *
	 * @deprecated in favor of #AbstractSpockFlowTest.createAddressWithOptions() method.
	 */
	@Deprecated(since = "2011")
	def createAddress(RESTClient client, user, format = JSON, basePathWithSite = getBasePathWithSite()) {
		createAddressWithOptions(client, user, format: format, basePathWithSite: basePathWithSite)
	}

	/**
	 *
	 * @param client REST client to use
	 * @param user
	 * @param addressOptions A map to provide various options like format, basePathWithSite and withBillingEmail. if these options are not provided, defaults are used.
	 * default format = JSON, default basePathWithSite = config.BASE_PATH_WITH_SITE and default withBillingEmail = false
	 * @return address if created.
	 */
	def createAddressWithOptions(Map addressOptions = [:], RESTClient client, user) {
		def defaultOptions = [
				format: JSON,
				basePathWithSite: getBasePathWithSite(),
				withBillingEmail: false
		]
		def options = defaultOptions + addressOptions
		def bodyContent = [
				'titleCode'      : CUSTOMER_TITLE_CODE,
				'firstName'      : CUSTOMER_FIRST_NAME,
				'lastName'       : CUSTOMER_LAST_NAME,
				'line1'          : CUSTOMER_ADDRESS_LINE1,
				'line2'          : CUSTOMER_ADDRESS_LINE2,
				'postalCode'     : CUSTOMER_ADDRESS_POSTAL_CODE,
				'phone'          : CUSTOMER_ADDRESS_PHONE,
				'town'           : CUSTOMER_ADDRESS_TOWN,
				'country.isocode': CUSTOMER_ADDRESS_COUNTRY_ISO_CODE,
				'fields'         : FIELD_SET_LEVEL_FULL
		]
		if (options.withBillingEmail) {
			bodyContent['email'] = CUSTOMER_EMAIL
		}
		HttpResponseDecorator response = client.post(
				path: options.basePathWithSite + '/users/' + user.customerId + '/addresses',
				body: bodyContent,
				contentType: options.format,
				requestContentType: URLENC)
		with(response) {
			if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
			status == SC_CREATED
		}
		return response.data
	}

	/**
	 *
	 * @param client REST client to use
	 * @param user
	 * @param format Data format to be used, defaults to JSON, does not need to be provided
	 * @return
	 */
	def createAddressWithAllMappingFileds(RESTClient client, user, format = JSON, basePathWithSite = getBasePathWithSite()) {
		HttpResponseDecorator response = client.post(
				path: basePathWithSite + '/users/' + user.customerId + '/addresses',
				body: [
						'titleCode'      : CUSTOMER_TITLE_CODE,
						'firstName'      : CUSTOMER_FIRST_NAME,
						'lastName'       : CUSTOMER_LAST_NAME,
						'email'          : CUSTOMER_EMAIL,
						'line1'          : CUSTOMER_ADDRESS_LINE1,
						'line2'          : CUSTOMER_ADDRESS_LINE2,
						'postalCode'     : CUSTOMER_ADDRESS_POSTAL_CODE,
						'phone'          : CUSTOMER_ADDRESS_PHONE,
						'town'           : CUSTOMER_ADDRESS_TOWN,
						'country.isocode': CUSTOMER_ADDRESS_COUNTRY_ISO_CODE,
						'cellphone'      : CUSTOMER_ADDRESS_CELLPHONE,
						'district'       : CUSTOMER_ADDRESS_DISTRICT,
						'fields'         : FIELD_SET_LEVEL_FULL
				],
				contentType: format,
				requestContentType: URLENC)

		with(response) {
			if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
			status == SC_CREATED
		}

		return response.data
	}

	/**
	 * This method authorizes customer. If null (default) is provided as customer, it will authorize default customer.
	 * @param customer customer to be authorized, defaults to null
	 * @param client REST client to use
	 */
	protected void authorizeCustomer(RESTClient client, customer = null) {
		def username = CUSTOMER_USERNAME
		def password = CUSTOMER_PASSWORD_STRONG

		if (customer) {
			username = customer.id
			password = customer.password
		}

		def token = getOAuth2TokenUsingPassword(client, getClientId(), getClientSecret(), username, password)
		addAuthorization(client, token)
	}

	/**
	 * This method authorizes customer manager. If null is provided as customerManager, it will authorize default customer manager.
	 * @param customerManager user with customer manager permissions, defaults to null
	 * @param client REST client to use
	 */
	protected void authorizeCustomerManager(RESTClient client, customerManager = null) {
		def username = CUSTOMER_MANAGER_USERNAME
		def password = CUSTOMER_MANAGER_PASSWORD

		if (customerManager) {
			username = customerManager.id
			password = customerManager.password
		}

		def token = getOAuth2TokenUsingPassword(client, getClientId(), getClientSecret(), username, password)
		addAuthorization(client, token)
	}

	protected void authorizeGuest(RESTClient client) {
		//throw new UnsupportedOperationException("Please provide an implementation for authorizing a guest")
	}

	/**
	 * This method creates a cart
	 * @param client REST client to use
	 * @param customer customer for whom cart should be created
	 * @param format data format to be used
	 * @return created cart
	 */
	protected createCart(RESTClient client, customer, format, basePathWithSite = getBasePathWithSite()) {
		String userId = customer.customerId;
		if (customer.id == "anonymous") {
			userId = "anonymous";
		}
		def cart = returningWith(client.post(
				path: basePathWithSite + '/users/' + userId + '/carts',
				query: ['fields': FIELD_SET_LEVEL_FULL],
				contentType: format,
				requestContentType: URLENC), {
			if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
			status == SC_OK
		}).data
		return cart
	}

	/**
	 * This method saves a cart
	 * @param client REST client to use
	 * @param customer customer for whom cart should be saved
	 * @param cart cart to be saved
	 * @param format data format to be used
	 * @return saved cart
	 */
	protected saveCart(RESTClient client, customer, cart, format, basePathWithSite = getBasePathWithSite()) {
		def savedCart = returningWith(client.patch(
				path: basePathWithSite + '/users/' + customer.customerId + '/carts/' + cart.code + '/save',
				query: ['fields': FIELD_SET_LEVEL_FULL],
				contentType: format,
				requestContentType: URLENC), {
			if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
			status == SC_OK
		}).data
		return savedCart
	}

	/**
	 * This method saves a cart
	 * @param client REST client to use
	 * @param customer customer for whom cart should be saved
	 * @param cart cart to be saved
	 * @param format data format to be used
	 * @return saved cart
	 */
	protected saveCartWithRequestBody(RESTClient client, customer, cart, format, basePathWithSite = getBasePathWithSite()) {
		def savedCart = returningWith(client.patch(
				path: basePathWithSite + '/users/' + customer.customerId + '/carts/' + cart.code + '/savedCart',
				query: ['fields': FIELD_SET_LEVEL_FULL],
				body: ["name": "testName", "description": "testDescription"],
				contentType: format,
				requestContentType: JSON), {
			if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
			status == SC_OK
		}).data
		return savedCart
	}
}
