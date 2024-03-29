package com.spark.lms;

import com.spark.lms.model.Category;
import com.spark.lms.service.BookService;
import com.spark.lms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.spark.lms.common.Constants;
import com.spark.lms.model.User;
import com.spark.lms.service.UserService;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookService bookService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        initDatabaseEntities();
    }


    private void initDatabaseEntities() {

        if( userService.getAllUsers().size() == 0) {
            userService.addNew(new User("Ad","Mr. Admin", "admin", "admin", Constants.ROLE_ADMIN));
            userService.addNew(new User("Us","Mr. Limam", "limam", "limam", Constants.ROLE_USER));
        }
        if(categoryService.getAllBySort().size() == 0) {
            categoryService.addNew(new Category("Fiction", "Fic", "This category is for fiction books."));
            categoryService.addNew(new Category("Non-Fiction", "Fic", "This category is for non-fiction books."));
            categoryService.addNew(new Category("Science", "Sci", "This category is for science books."));
        }



    }
}