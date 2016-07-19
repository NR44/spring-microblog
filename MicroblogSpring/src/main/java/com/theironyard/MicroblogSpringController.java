package com.theironyard;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Nigel on 7/18/16.
 */
@Controller
public class MicroblogSpringController {
    public static final String SESSION_USERNAME = "userName";
    public List<Message> submittedMessages = new ArrayList<>();

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session){
        String userName = (String) session.getAttribute(SESSION_USERNAME);
        model.addAttribute("userName", userName);
        model.addAttribute("messageList", submittedMessages);
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName){
        session.setAttribute(SESSION_USERNAME, userName);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path="/add-message", method = RequestMethod.POST)
    public String addMessage(String message){
        Message submittedMessage = new Message(submittedMessages.size()+1, message);
        submittedMessages.add(submittedMessage);
        return "redirect:/";
    }

    @RequestMapping(path ="/delete-message", method = RequestMethod.POST)
    //how do you request he query parameter into the method.
    public String deleteMessage(HttpServletRequest request){
        String idString = request.getParameter("id");
        int id = Integer.valueOf(idString);
        int deleteId = 0;

        Iterator<Message> iterator = submittedMessages.iterator();
        while(iterator.hasNext()){
            Message message = iterator.next();
            if(message.getId() == id){
                iterator.remove();
            }
        }
        return "redirect:/";
    }
}