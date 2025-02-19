This recipe provides everything you need to install SAP Hybris Commerce B2C accelerator and marketplace for China with Flash Buy, Alipay Integration, Baidu Map Integration, Wechat Pay Integration and Customer Coupon addons. 

Required Configurations:
* For features that require a Baidu API key (such as the Store Locator, which uses Baidu Maps), you need to set the “baiduApiKey”
* For features which related to Alipay Integration and Wechat Pay Integration, please refer to https://help.hybris.com/
* For Kyma integration + ApiRegistry, optionally, ccv2.services.api.url.0 property should be set to a server url reachable by kyma instead of https://localhost:9002.

 Platform Setup:
   1. Setup platform using command ./install.sh -r cx_china setup -A initAdminPassword=[password]
   2. Initialize platform using command ./install.sh -r cx_china initialize -A initAdminPassword=[password]
   3. Start platform using command ./install.sh -r cx_china start