package cn.harveychan.kaimaa.service.impl;

import cn.harveychan.kaimaa.NotFoundException;
import cn.harveychan.kaimaa.dao.TagRepository;
import cn.harveychan.kaimaa.po.Tag;
import cn.harveychan.kaimaa.service.TagService;
import cn.harveychan.kaimaa.util.SnowflakeIdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private SnowflakeIdWorker idWorker;

    @Override
    @Transactional
    public Tag saveTag(Tag tag) {
        tag.setId(idWorker.nextId());
        tag.setCreateTime(new Date());
        tag.setUpdateTime(tag.getCreateTime());
        return tagRepository.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagRepository.findById(id).orElse(null);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return tagRepository.findTop(pageable);
    }

    @Override
    public List<Tag> listTag(String ids) {
        return tagRepository.findAllById(convertToList(ids));
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (ids != null && !"".equals(ids)) {
            String[] idArr = ids.split(",");
            for (int i = 0; i < idArr.length; i++) {
                list.add(new Long(idArr[i]));
            }
        }
        return list;
    }

    @Override
    @Transactional
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.findById(id).orElse(null);
        if (t == null) {
            throw new NotFoundException("该标签不存在");
        }
        BeanUtils.copyProperties(tag, t);
        return tagRepository.save(t);
    }

    @Override
    @Transactional
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
