package io.lozzikit.users.service;

import io.lozzikit.users.entities.TeamEntity;
import io.lozzikit.users.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamRepository teamRepository;

    /**
     * @brief Get a specific team in the database
     * @param id of the team
     * @return The team
     */
    @Override
    public TeamEntity getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    /**
     * @brief Get the list of all teams sort by id
     * @param page page number
     * @param size number of user in a page
     * @param order ASC or DESC order
     * @return The list of teams
     */
    @Override
    public List<TeamEntity> getTeamListWithPagination(int page, int size, Sort.Direction order) {
        return teamRepository.findAll(new PageRequest(page, size, order, "id")).getContent();
    }

    @Override
    public List<TeamEntity> getTeamList() {
        List<TeamEntity> teams = new ArrayList<>();

        teams.addAll(teamRepository.findAll());

        return teams;
    }

    @Override
    public TeamEntity save(TeamEntity t) {
        return teamRepository.save(t);
    }
}