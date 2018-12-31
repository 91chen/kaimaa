package cn.harveychan.kaimaa.service.impl;

import cn.harveychan.kaimaa.NotFoundException;
import cn.harveychan.kaimaa.dao.TypeRepository;
import cn.harveychan.kaimaa.po.Type;
import cn.harveychan.kaimaa.service.TypeService;
import cn.harveychan.kaimaa.util.SnowflakeIdWorker;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private SnowflakeIdWorker idWorker;

    @Override
    @Transactional
    public Type saveType(Type type) {
        type.setId(idWorker.nextId());
        type.setCreateTime(new Date());
        type.setUpdateTime(type.getCreateTime());
        return typeRepository.save(type);
    }

    @Override
    public Type getType(Long id) {
        return typeRepository.findById(id).orElse(null);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return typeRepository.findTop(pageable);
    }

    @Override
    @Transactional
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.findById(id).orElse(null);
        if (t == null) {
            throw new NotFoundException("该分类不存在");
        }
        BeanUtils.copyProperties(type, t);
        return typeRepository.save(t);
    }

    @Override
    @Transactional
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
