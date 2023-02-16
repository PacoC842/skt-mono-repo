package com.skytouch.consumer;//package com.company.app.consumer;
//
//import com.company.app.model.Book;
//import com.company.app.model.Booklist;
//import com.company.app.service.BookService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Component;
//
//
//@Component
//@Slf4j
//public class ReplyingKafkaConsumer {
//
//    BookService bookService;
//
//    public ReplyingKafkaConsumer(BookService bookService) {
//        this.bookService = bookService;
//    }
//
//    @KafkaListener(topics = "${kafka.topic.request-topic}")
//    @SendTo
//    public Booklist listen(Booklist Booklist) throws InterruptedException {
//        Booklist list = bookService.getAll();
//        log.info("Message received, and we have tried to send it back");
//        return list;
//    }
//
//	@KafkaListener(topics = "${kafka.topic.create-entry-topic}")
//	public String Onlylisten(Book book) throws InterruptedException {
//		log.info("Message received, book name is {} and the author is {}", book.getName(), book.getAuthor() );
//
//		bookService.createBook(book);
//		return "Instructions to create a book record received, bookname is {}".concat(book.getName());
//	}
//
//}
