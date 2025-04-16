package com.bys.formation.Controller;

import com.bys.formation.entities.Formation;
import com.bys.formation.services.FormationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pfe/api/formation")
@CrossOrigin(origins = "http://localhost:4200")  // تأكد من أن هذا مناسب لبيئة الإنتاج أيضًا
@Slf4j
public class FormationController {

    @Autowired
    private FormationService formationService;

    @GetMapping("/retrieve-all-formations")
    public ResponseEntity<List<Formation>> getFormations() {
        List<Formation> list = formationService.retrieveAllFormations();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // لا توجد بيانات
        }
        return new ResponseEntity<>(list, HttpStatus.OK);  // بيانات موجودة
    }

    @GetMapping("/retrieve-formation/{formation-id}")
    public ResponseEntity<Formation> retrieveFormation(@PathVariable("formation-id") Long formationId) {
        Formation formation = formationService.retrieveFormation(formationId);
        if (formation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // الفيديو غير موجود
        }
        return new ResponseEntity<>(formation, HttpStatus.OK);  // تم العثور على الفيديو
    }

    @PostMapping("/add-formation")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Formation> addFormation(@RequestBody Formation f) {
        if (f == null || f.getVideoUrl().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // تحقق من البيانات المدخلة
        }
        Formation formation = formationService.addFormation(f);
        return new ResponseEntity<>(formation, HttpStatus.CREATED);  // تم إضافة الفيديو بنجاح
    }

    @PutMapping("/update-formation")
    public ResponseEntity<Formation> updateFormation(@RequestBody Formation f) {
        if (f == null || f.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // تحقق من البيانات المدخلة
        }
        Formation updatedFormation = formationService.updateFormation(f);
        return new ResponseEntity<>(updatedFormation, HttpStatus.OK);  // تم تحديث الفيديو بنجاح
    }

    @DeleteMapping("/delete-formation/{formation-id}")
    public ResponseEntity<Void> deleteFormation(@PathVariable("formation-id") Long formationId) {
        Formation formation = formationService.retrieveFormation(formationId);
        if (formation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // الفيديو غير موجود
        }
        formationService.deleteFormation(formationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // تم الحذف بنجاح
    }
}
