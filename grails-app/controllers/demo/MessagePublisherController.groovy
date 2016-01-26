package demo

import java.util.concurrent.CountDownLatch;

class MessagePublisherController {

    CountDownLatch latch
    def index() {
        def nQuotes = 10
        long start = System.currentTimeMillis();
        latch = new CountDownLatch(nQuotes)

        (1..nQuotes).each {
            notify("quotes", latch)
        }

        latch.await()

        long elapsed = System.currentTimeMillis() - start

        println("Elapsed time: " + elapsed + "ms")
        println("Average time per quote: " + elapsed / nQuotes + "ms")

        render text:"Loaded index"
    }
}
