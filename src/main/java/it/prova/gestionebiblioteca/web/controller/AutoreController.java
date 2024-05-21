package it.prova.gestionebiblioteca.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionebiblioteca.dto.AutoreDTO;
import it.prova.gestionebiblioteca.model.Autore;
import it.prova.gestionebiblioteca.service.AutoreService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/autore")
public class AutoreController {

    @Autowired
    private AutoreService autoreService;

    @GetMapping
    public ModelAndView listAllAutori() {
        ModelAndView mv = new ModelAndView();
        List<Autore> autori = autoreService.listAllElements();
        mv.addObject("autori_list_attribute", AutoreDTO.createAutoreDTOListFromModelList(autori));
        mv.setViewName("autore/list");
        return mv;
    }

    @GetMapping("/insert")
    public String createAutore(Model model) {
        model.addAttribute("insert_autore_attr", new AutoreDTO());
        return "autore/insert";
    }

    @PostMapping("/save")
    public String saveAutore(@Valid @ModelAttribute("insert_autore_attr") AutoreDTO autoreDTO, BindingResult result,
                             RedirectAttributes redirectAttrs) {

        if (result.hasErrors()) {
            return "autore/insert";
        }
        autoreService.inserisciNuovo(autoreDTO.buildAutoreModel());

        redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
        return "redirect:/autore";
    }

    @GetMapping("/search")
    public String searchAutore() {
        return "autore/search";
    }

    @PostMapping("/list")
    public String listAutori(AutoreDTO autoreExample, ModelMap model) {
        List<Autore> autori = autoreService.findByExample(autoreExample.buildAutoreModel());
        model.addAttribute("autori_list_attribute", AutoreDTO.createAutoreDTOListFromModelList(autori));
        return "autore/list";
    }

    @GetMapping("/show/{idAutore}")
    public String showAutore(@PathVariable(required = true) Long idAutore, Model model) {
        model.addAttribute("show_autore_attr",
                AutoreDTO.buildAutoreDTOFromModel(autoreService.caricaSingoloElemento(idAutore)));
        return "autore/show";
    }

    @GetMapping("/edit/{idAutore}")
    public String editAutore(@PathVariable(required = true) Long idAutore, Model model) {
        model.addAttribute("edit_autore_attr",
                AutoreDTO.buildAutoreDTOFromModel(autoreService.caricaSingoloElemento(idAutore)));
        return "autore/edit";
    }

    @PostMapping("/update")
    public String updateAutore(@Valid @ModelAttribute("edit_autore_attr") AutoreDTO autoreDTO, BindingResult result,
                               RedirectAttributes redirectAttrs, HttpServletRequest request) {

        if (result.hasErrors()) {
            return "autore/edit";
        }
        autoreService.aggiorna(autoreDTO.buildAutoreModel());

        redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
        return "redirect:/autore";
    }

    @GetMapping("/preDelete/{idAutore}")
    public String prepareDelete(@PathVariable(required = true) Long idAutore, RedirectAttributes redirectAttrs,
                                Model model) {
        Autore temp = autoreService.caricaSingoloElementoConLibri(idAutore);
        if (temp.getLibri() != null && !temp.getLibri().isEmpty()) {
            redirectAttrs.addFlashAttribute("errorMessage",
                    "Impossibile rimuovere l'autore, ci sono ancora suoi libri");
            return "redirect:/autore";
        }
        model.addAttribute("toDelete_autore_attr",
                AutoreDTO.buildAutoreDTOFromModel(autoreService.caricaSingoloElemento(idAutore)));
        return "autore/delete";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(required = true) Long idAutore, RedirectAttributes redirectAttrs) {
        Autore temp = autoreService.caricaSingoloElementoConLibri(idAutore);
        if (temp.getLibri() != null && !temp.getLibri().isEmpty()) {
            redirectAttrs.addFlashAttribute("errorMessage",
                    "Impossibile rimuovere l'autore, ci sono ancora suoi libri");
            return "redirect:/autore";
        }
        autoreService.rimuovi(idAutore);
        redirectAttrs.addFlashAttribute("successMessage", "La rimozione è avvenuta con successo!");
        return "redirect:/autore";
    }
}
