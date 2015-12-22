package netgloo.controllers;

import java.util.List;

import netgloo.models.User;
import netgloo.search.UserSearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * MainController class
 * 
 * @author netgloo
 */
@Controller
public class MainController {

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------

  // Inject the UserSearch object
  @Autowired
  private UserSearch userSearch;


  // ------------------------
  // PUBLIC METHODS
  // ------------------------

  /**
   * Index main page.
   */
  @RequestMapping("/")
  @ResponseBody
  public String index() {
//    return "Proudly handcrafted by " +
//        "<a href='http://netgloo.com/en'>netgloo</a> :)";
    return "user";
  }


  /**
   * Show search results for the given query.
   *
   * @param q The search query.
   */
  @RequestMapping("/search")
  public String search(String q, Model model) {
    List<User> searchResults = null;
    try {
      searchResults = userSearch.search(q);
    }
    catch (Exception ex) {
      // here you should handle unexpected errors
      // ...
      // throw ex;
    }
    model.addAttribute("searchResults", searchResults);
    return "search";
  }
  @RequestMapping(value="/user", method= RequestMethod.POST)
  public String greetingSubmit(@ModelAttribute User user, Model model) {
    model.addAttribute("user", user);
    userSearch.save(user);
    return "result";
  }
    @RequestMapping(value="/user", method=RequestMethod.GET)
    public String greetingForm(Model model) {
        model.addAttribute("user", new User());
        return "user";
    }

} // class MainController
