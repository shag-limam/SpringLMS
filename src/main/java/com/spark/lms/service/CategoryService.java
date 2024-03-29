package com.spark.lms.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.spark.lms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spark.lms.model.Category;
import com.spark.lms.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Long getTotalCount() {
		return categoryRepository.count();
	}
	
	public List<Category> getAllBySort() {
		return categoryRepository.findAllByOrderByNameAsc();
	}

	public List<Category> getAll() {
		return categoryRepository.findAll();
	}
	
	public Category get(Long id) {
		return categoryRepository.findById(id).get();
	}
	
	public Category addNew(Category category) {
		category.setCreateDate(new Date());
		return categoryRepository.save(category);
	}
	public Category update(Category category) {
		// Vérifiez si la catégorie existe déjà dans la base de données
		Optional<Category> existingCategory = categoryRepository.findById(category.getId());
		if (existingCategory.isPresent()) {
			// Mettez à jour les champs de la catégorie existante avec les nouvelles valeurs
			Category updatedCategory = existingCategory.get();
			updatedCategory.setName(category.getName());
			updatedCategory.setShortName(category.getShortName());
			updatedCategory.setNotes(category.getNotes());
			// Vous pouvez ajouter d'autres champs à mettre à jour ici

			// Enregistrez la catégorie mise à jour dans la base de données
			return categoryRepository.save(updatedCategory);
		} else {
			// Si la catégorie n'existe pas, vous pouvez choisir de renvoyer null ou de lever une exception
			return null;
			// Ou
			// throw new EntityNotFoundException("Category not found with id: " + category.getId());
		}
	}


	public Category save(Category category) {
		return categoryRepository.save(category);
	}
	
	public void delete(Category category) {
		categoryRepository.delete(category);
	}
	
	public void delete(Long id) {
		categoryRepository.deleteById(id);
	}
	
	public boolean hasUsage(Category category) {
		return category.getBooks().size()>0;
	}
}
