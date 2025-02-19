/**
 * CXEC-40505: Could not close QTX pushSurvey popup
 * In canvg.js, there are codes to override Promise, which is used as a sort of polyfill for IE exporting PNGs.
 * It is conflicting with the Promise in QTX PX-API, which uses Promise.finally()
 * As we do not support IE anymore, we can remove canvg.js by adding an empty file in /web/js/{js_path_in_zk}.
 * Guide: https://www.zkoss.org/wiki/ZK_Configuration_Reference/zk.xml/The_Library_Properties/org.zkoss.web.util.resource.dir#Overriding_JavaScript_in_a_ZK_JAR
 * More in details: ZK Ticket: https://potix.freshdesk.com/support/tickets/16666
 */
