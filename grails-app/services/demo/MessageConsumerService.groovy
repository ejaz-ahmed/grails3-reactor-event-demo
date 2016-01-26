package demo

import grails.transaction.Transactional
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import reactor.spring.context.annotation.Consumer
import reactor.spring.context.annotation.Selector

@Transactional
@Consumer
class MessageConsumerService {

    @Selector('quotes')
    def messageReceiver(def eventData) {
        RestBuilder builder = new RestBuilder()
        RestResponse response

        response = builder.get("http://gturnquist-quoters.cfapps.io/api/random")

        println("Quote $eventData.count :" +response.json.value.quote)
        eventData.countDown()
    }
}
