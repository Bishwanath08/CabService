{
	"info": {
		"_postman_id": "2ff21355-32d5-41bb-81ea-e21d4ac49a6f",
		"name": "CabService",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "44380712",
		"_collection_link": "https://bishwanath-6014551.postman.co/workspace/Bishwanath-'s-Workspace~5f7d3d00-69c1-42e3-832a-17f0fc96b931/collection/44380712-2ff21355-32d5-41bb-81ea-e21d4ac49a6f?action=share&source=collection_link&creator=44380712"
	},
	"item": [
		{
			"name": "register-driver",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n  \"mobile\": \"7079752288\"\r\n}\r\n",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8088/api/user/register/driver",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8088",
					"path": [
						"api",
						"user",
						"register",
						"driver"
					]
				}
			},
			"response": []
		},
		{
			"name": "regidter Driver-verify_otp",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8088/api/user/verify-otp?mobile=7079752288&otp=8674",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8088",
					"path": [
						"api",
						"user",
						"verify-otp"
					],
					"query": [
						{
							"key": "mobile",
							"value": "7079752288"
						},
						{
							"key": "otp",
							"value": "8674"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "driver-Login",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n  \"mobile\": \"7079752288\"\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8088/api/user/login/driver",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8088",
					"path": [
						"api",
						"user",
						"login",
						"driver"
					]
				}
			},
			"response": []
		},
		{
			"name": "driver-login-otp",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "mobile",
						"value": "5555555555",
						"type": "text"
					},
					{
						"key": "otp",
						"value": "6535",
						"type": "text"
					}
				],
				"url": {
					"raw": "http://localhost:8088/api/user/verify-login/driver?mobile=7079752288&otp=1482",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8088",
					"path": [
						"api",
						"user",
						"verify-login",
						"driver"
					],
					"query": [
						{
							"key": "mobile",
							"value": "7079752288"
						},
						{
							"key": "otp",
							"value": "1482"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "register-customer",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n  \"mobile\": \"6205435487\"\r\n}\r\n",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8088/api/user/register/customer",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8088",
					"path": [
						"api",
						"user",
						"register",
						"customer"
					]
				}
			},
			"response": []
		},
		{
			"name": "registerCustomer-verify-otp",
			"request": {
				"method": "POST",
				"header": [],
				"url": {
					"raw": "http://localhost:8088/api/user/verify-otp/customer?mobile=6205435487&otp=8967",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8088",
					"path": [
						"api",
						"user",
						"verify-otp",
						"customer"
					],
					"query": [
						{
							"key": "mobile",
							"value": "6205435487"
						},
						{
							"key": "otp",
							"value": "8967"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "Customer-Login",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n  \"mobile\": \"6205435487\"\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8088/api/user/login/customer",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8088",
					"path": [
						"api",
						"user",
						"login",
						"customer"
					]
				}
			},
			"response": []
		},
		{
			"name": "customer-login-otp",
			"request": {
				"method": "POST",
				"header": [],
				"url": {
					"raw": "http://localhost:8088/api/user/verify-login/customer?mobile=6205435487&otp=8852",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8088",
					"path": [
						"api",
						"user",
						"verify-login",
						"customer"
					],
					"query": [
						{
							"key": "mobile",
							"value": "6205435487"
						},
						{
							"key": "otp",
							"value": "8852"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "driver-Kyc",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "formdata",
					"formdata": [
						{
							"key": "userId",
							"value": "16",
							"type": "text"
						},
						{
							"key": "adharNumber",
							"value": "5487954216325",
							"type": "text"
						},
						{
							"key": "adharName",
							"value": "RishuKumar",
							"type": "text"
						},
						{
							"key": "adharImage",
							"type": "file",
							"src": "/D:/cabService/src/main/resources/uploads/adhar/driver1.jpg"
						},
						{
							"key": "panNumber",
							"value": "AAGT665Q88JDU",
							"type": "text"
						},
						{
							"key": "panName",
							"value": "RishuKumar",
							"type": "text"
						},
						{
							"key": "dob",
							"value": "2000-05-11",
							"type": "text"
						},
						{
							"key": "panImage",
							"type": "file",
							"src": "/D:/cabService/src/main/resources/uploads/pan/pan images.jpeg"
						},
						{
							"key": "",
							"value": "",
							"type": "text",
							"disabled": true
						}
					]
				},
				"url": {
					"raw": "http://localhost:8088/kyc/kyc/submit",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8088",
					"path": [
						"kyc",
						"kyc",
						"submit"
					],
					"query": [
						{
							"key": "",
							"value": "",
							"disabled": true
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "Add Vehicle",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "formdata",
					"formdata": [
						{
							"key": "userId",
							"value": "16",
							"type": "text"
						},
						{
							"key": "adharNumber",
							"value": "5487954216325",
							"type": "text"
						},
						{
							"key": "adharName",
							"value": "RishuKumar",
							"type": "text"
						},
						{
							"key": "adharImage",
							"type": "file",
							"src": "/D:/cabService/src/main/resources/uploads/adhar/driver1.jpg"
						},
						{
							"key": "panNumber",
							"value": "AAGT665Q88JDU",
							"type": "text"
						},
						{
							"key": "panName",
							"value": "RishuKumar",
							"type": "text"
						},
						{
							"key": "dob",
							"value": "2000-05-11",
							"type": "text"
						},
						{
							"key": "panImage",
							"type": "file",
							"src": "/D:/cabService/src/main/resources/uploads/pan/pan images.jpeg"
						},
						{
							"key": "",
							"value": "",
							"type": "text",
							"disabled": true
						}
					]
				},
				"url": {
					"raw": "http://localhost:8088/vehicles/add?driverId=16&vehicleType=XUV&vehicleNumber=MH06WE2200&status=INACTIVE",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8088",
					"path": [
						"vehicles",
						"add"
					],
					"query": [
						{
							"key": "driverId",
							"value": "16"
						},
						{
							"key": "vehicleType",
							"value": "XUV"
						},
						{
							"key": "vehicleNumber",
							"value": "MH06WE2200"
						},
						{
							"key": "status",
							"value": "INACTIVE"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "Current Vehicle",
			"request": {
				"method": "PUT",
				"header": [],
				"url": {
					"raw": "http://localhost:8088/vehicles/set-current?driverId=16&vehicleId=1",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8088",
					"path": [
						"vehicles",
						"set-current"
					],
					"query": [
						{
							"key": "driverId",
							"value": "16"
						},
						{
							"key": "vehicleId",
							"value": "1"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "Change Vehicle Status",
			"request": {
				"method": "PUT",
				"header": [],
				"url": {
					"raw": "http://localhost:8088/vehicles/status/2?status=ACTIVE",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8088",
					"path": [
						"vehicles",
						"status",
						"2"
					],
					"query": [
						{
							"key": "status",
							"value": "ACTIVE"
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "Delete Vehicle",
			"request": {
				"method": "DELETE",
				"header": [],
				"url": {
					"raw": "http://localhost:8088/vehicles/delete/10",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8088",
					"path": [
						"vehicles",
						"delete",
						"10"
					]
				}
			},
			"response": []
		},
		{
			"name": "List All Vehicals of Driver",
			"request": {
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8088/vehicles/list/36",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8088",
					"path": [
						"vehicles",
						"list",
						"36"
					]
				}
			},
			"response": []
		},
		{
			"name": "Active Driver List",
			"request": {
				"method": "GET",
				"header": [
					{
						"key": "Authorization",
						"value": "3cb6705b-e6ff-497a-98a0-dfb6d1a1fa94",
						"type": "text"
					}
				],
				"url": {
					"raw": "http://localhost:8088/api/user/available-drivers?=",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8088",
					"path": [
						"api",
						"user",
						"available-drivers"
					],
					"query": [
						{
							"key": "",
							"value": ""
						}
					]
				}
			},
			"response": []
		},
		{
			"name": "Update Vehicle",
			"request": {
				"method": "PUT",
				"header": [],
				"url": {
					"raw": "http://localhost:8088/vehicles/update/13?driverId=37&vehicleType=SUV&status=ACTIVE&vehicleNumber=JK23AH00009",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8088",
					"path": [
						"vehicles",
						"update",
						"13"
					],
					"query": [
						{
							"key": "driverId",
							"value": "37"
						},
						{
							"key": "vehicleType",
							"value": "SUV"
						},
						{
							"key": "status",
							"value": "ACTIVE"
						},
						{
							"key": "vehicleNumber",
							"value": "JK23AH00009"
						}
					]
				}
			},
			"response": []
		}
	]
}
